package site.junggam.procurement_system.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.InventoryMapper;
import site.junggam.procurement_system.mapper.ReleaseMapper;
import site.junggam.procurement_system.repository.InventoryHistoryRepository;
import site.junggam.procurement_system.repository.InventoryRepository;
import site.junggam.procurement_system.repository.ReleaseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;
    private final InventoryHistoryRepository inventoryHistoryRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;


    @Override
    public ReleaseDTO getReleaseRequest(String releaseCode) {
        log.info("출고요청상세보기");
        Optional<Release> release = releaseRepository.findById(releaseCode);
        if (release.isPresent()) {
            ReleaseDTO releaseDTO = releaseMapper.toDTO(release.get());
            return releaseDTO;
        }else return null;
    }

    @Override
    public void saveReleaseRequest(ReleaseDTO releaseDTO) {
        log.info("서비스까지 온 데이터 DTO"+releaseDTO);
        Release entity=releaseMapper.toEntity(releaseDTO);
        log.info("변형된엔티티"+entity);
        releaseRepository.save(entity);
    }

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<ReleaseDTO, Release> getReleaseList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("releaseCode").descending()); //나주에 바꿀것
//            Page<Release> result = releaseRepository.findAll(pageable);
//            Function<Release, ReleaseDTO> fn = (release -> {
//                ReleaseDTO dto = releaseMapper.toDTO(release);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("releaseCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QRelease qRelease = QRelease.release;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Release> query = queryFactory.selectFrom(qRelease)
                    .where(getReleaseSearch(pageRequestDTO))
                    .orderBy(qRelease.releaseCode.desc());

            // 페이지 처리
            List<Release> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Release, ReleaseDTO> fn = release -> {
                ReleaseDTO dto = releaseMapper.toDTO(release);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    @Override
    public void saveRelease(ReleaseDTO releaseDTO) {
        log.info("서비스까지 온 데이터 DTO"+releaseDTO);
        String releaseCode = releaseDTO.getReleaseCode();
        Release release=releaseRepository.findById(releaseCode).get();

        //인벤토리 저장
        String materialCode = releaseDTO.getMaterialCode();
        LocalDateTime releaseDateTime = releaseDTO.getReleaseDate();
        int releaseQuantity = releaseDTO.getReleaseQuantity();
        Inventory inventory= inventoryRepository.findById(materialCode).get();
        //인벤토리 히스토리 먼저 저장하고
        InventoryHistory inventoryHistory=InventoryHistory.builder()
                .inventory(inventory)
                .transactionType(InventoryHistoryStatus.RELEASE)
                .transactionReference(releaseCode)
                .transactionDate(releaseDateTime)
                .quantityChange(releaseQuantity*(-1))
                .finalQuantity(inventory.getMaterialQuantity()+releaseQuantity*(-1))
                .build();
        int finalQauntity =inventoryHistoryRepository.save(inventoryHistory).getFinalQuantity();
        //인벤토리 정보 변경
        inventory.setMaterialQuantity(finalQauntity);
        inventory.setReleaseDesireSumQuantity(inventory.getReleaseDesireSumQuantity()-releaseQuantity);
        inventoryRepository.save(inventory);

        //출고정보 저장
        release.setReleaseDate(releaseDTO.getReleaseDate());
        release.setReleaseMemo(releaseDTO.getReleaseMemo());
        release.setReleaseQuantity(releaseDTO.getReleaseQuantity());
        release.setReleaseStaus(ReleaseStaus.COMPLETED);
        releaseRepository.save(release);
    }


    //CYH : 24.08.30 추가
    private BooleanBuilder getReleaseSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();

        QRelease qRelease = QRelease.release;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qRelease.releaseStaus.eq(ReleaseStaus.PENDING));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qRelease.releaseDesireDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder typeBuilder = new BooleanBuilder();

            // Ensure that qWarehousing.purchaseOrder.procurementPlan.material is not null
            if (type.contains("1")) {
                if (keyword != null && qRelease.material != null) {
                    typeBuilder.or(qRelease.material.materialName.contains(keyword));
                }
            }

            if (type.contains("2")) {
                if (keyword != null && qRelease.material != null) {
                    typeBuilder.or(qRelease.material.materialCode.contains(keyword));
                }
            }

            if (type.contains("3")) {
                if (keyword != null && qRelease.releaseRequestDept != null) {
                    typeBuilder.or(qRelease.releaseRequestDept.contains(keyword));
                }
            }

            builder.and(typeBuilder);
        }

        return builder;
    }


}
