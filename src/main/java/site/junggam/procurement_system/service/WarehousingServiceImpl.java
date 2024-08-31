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
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.WarehousingHistoryMapper;
import site.junggam.procurement_system.mapper.WarehousingMapper;
import site.junggam.procurement_system.repository.InventoryRepository;
import site.junggam.procurement_system.repository.WarehousingHistoryRepository;
import site.junggam.procurement_system.repository.WarehousingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehousingServiceImpl implements WarehousingService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final WarehousingRepository warehousingRepository;
    private final WarehousingMapper warehousingMapper;

    private final WarehousingHistoryRepository warehousingHistoryRepository;
    private final WarehousingHistoryMapper warehousingHistoryMapper;
    private final InventoryRepository inventoryRepository;

    @Override
    public WarehousingDTO getWarehousing(String warehousingId) {
        Optional<Warehousing> result = warehousingRepository.findById(warehousingId);
        if (result.isPresent()) {
            WarehousingDTO warehousingDTO=warehousingMapper.toDTO(result.get());
            warehousingDTO.setWarehousingHistoryDTOS(getWarehousingHistory(warehousingId));
            return warehousingDTO;
        }
        return null;
    }

    @Override
    public String saveWarehousingHistory(WarehousingHistoryDTO warehousingHistoryDTO) {

        //입고히스토리 정보 저장
        log.info("들어온 DTO값"+warehousingHistoryDTO);
        String warehousingCode=warehousingHistoryDTO.getWarehousingCode();
        int historyNum;
        historyNum=1+warehousingHistoryRepository.findByWarehousingCode(warehousingCode).size();
        String warehousingHistoryCode=warehousingCode+"-"+historyNum;
        warehousingHistoryDTO.setWarehousingHistoryCode(warehousingHistoryCode);

        warehousingHistoryRepository.save(warehousingHistoryMapper.toEntity(warehousingHistoryDTO));

        //인벤토리 저장
        String materialCode = warehousingHistoryDTO.getMaterialCode();
        Inventory inventory= inventoryRepository.findById(materialCode).get();


        //여기는 입고 정보를 저장
        Warehousing warehousing=warehousingRepository.findById(warehousingCode).get();
        int orderQauntity=getWarehousing(warehousingCode).getPurchaseOrderDTO().getProcurementPlanQuantity();
        int warehousingQauntity = warehousingHistoryDTO.getWarehousingQuantity();
        boolean statusReult = warehousingHistoryDTO.getWarehousingHistoryStatus().equals(WarehousingHistoryStatus.WAREHOUSING);
        if(statusReult && orderQauntity==warehousingQauntity){
            warehousing.changeWarehousingStatus(WarehousingStatus.COMPLETED);
            warehousingRepository.save(warehousing);
        }
        return warehousingHistoryCode;
    }

    @Override
    public List<WarehousingHistoryDTO> getWarehousingHistory(String warehousingId) {
        List<WarehousingHistory> warehousingHistoryList=warehousingHistoryRepository.findByWarehousingCode(warehousingId);
        return warehousingHistoryMapper.toDtos(warehousingHistoryList);
    }


    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<WarehousingDTO, Warehousing> getAllWarehousingList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrder.purchaseOrderDate").ascending());
//            Page<Warehousing> result = warehousingRepository.findAllByStatus(WarehousingStatus.PENDING, pageable);
//            Function<Warehousing, WarehousingDTO> fn =(warehousing->{
//                WarehousingDTO dto = warehousingMapper.toDTO(warehousing);
//                return dto;
//            });
//            return new PageResultDTO<>(result,fn);
//        }catch (Exception e) {
//            log.error(e);
//            throw e;
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QWarehousing qWarehousing = QWarehousing.warehousing;
            QMaterial qMaterial = QMaterial.material;
            QPurchaser qPurchaser = QPurchaser.purchaser;
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Warehousing> query = queryFactory.selectFrom(qWarehousing)
                    .where(getWarehousingSearch(pageRequestDTO))
                    .orderBy(qWarehousing.warehousingCode.desc());

            // 페이지 처리
            List<Warehousing> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Warehousing, WarehousingDTO> fn = warehousing -> {
                WarehousingDTO dto = warehousingMapper.toDTO(warehousing);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getWarehousingSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        LocalDate startDate2 = pageRequestDTO.getStartDate2();
        LocalDate endDate2 = pageRequestDTO.getEndDate2();

        QWarehousing qWarehousing = QWarehousing.warehousing;
        QMaterial qMaterial = QMaterial.material;
        QPurchaser qPurchaser = QPurchaser.purchaser;
        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qWarehousing.warehousingCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qPurchaseOrder.purchaseOrderDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }
        if (startDate2 != null && endDate2 != null) {
            builder.and(qWarehousing.purchaseOrder.procurementPlan.procurementPlanDeadLine.between(startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder typeBuilder = new BooleanBuilder();

            // Ensure that qWarehousing.purchaseOrder.procurementPlan.material is not null
            if (type.contains("1")) {
                if (keyword != null && qWarehousing.purchaseOrder != null &&
                        qWarehousing.purchaseOrder.procurementPlan != null &&
                        qWarehousing.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qWarehousing.purchaseOrder.procurementPlan.material.materialName.contains(keyword));
                }
            }

            if (type.contains("2")) {
                if (keyword != null && qWarehousing.purchaseOrder != null &&
                        qWarehousing.purchaseOrder.procurementPlan != null &&
                        qWarehousing.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qWarehousing.purchaseOrder.procurementPlan.material.materialCode.contains(keyword));
                }
            }

            if (type.contains("3")) {
                if (keyword != null && qWarehousing.purchaseOrder != null &&
                        qWarehousing.purchaseOrder.procurementPlan != null &&
                        qWarehousing.purchaseOrder.procurementPlan.material != null &&
                        qWarehousing.purchaseOrder.procurementPlan.material.contract != null &&
                        qWarehousing.purchaseOrder.procurementPlan.material.contract.purchaser != null) {
                    typeBuilder.or(qWarehousing.purchaseOrder.procurementPlan.material.contract.purchaser.purchaserName.contains(keyword));
                }
            }

            builder.and(typeBuilder);
        }

        return builder;
    }


}
