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
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.QPurchaser;
import site.junggam.procurement_system.mapper.PurchaserMapper;
import site.junggam.procurement_system.repository.PurchaserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class PurchaserServiceImpl implements PurchaserService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final PurchaserRepository purchaserRepository;
    private final PurchaserMapper purchaserMapper;


    @Override
    public PurchaserDTO getPurchaser(String purchaserCode) {
        log.info("회사정보 상세보기 서비스");
        Optional<Purchaser> result = purchaserRepository.findById(purchaserCode);
        return result.map(purchaserMapper::toDto).orElse(null);
    }

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<PurchaserDTO, Purchaser> getPurchaserList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaserCode").descending()); //나주에 바꿀것
//            Page<Purchaser> result = purchaserRepository.findAll(pageable);
//            Function<Purchaser, PurchaserDTO> fn = (purchaser -> {
//                PurchaserDTO dto = purchaserMapper.toDto(purchaser);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaserCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QPurchaser qPurchaser = QPurchaser.purchaser;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Purchaser> query = queryFactory.selectFrom(qPurchaser)
                    .where(getPurchaserSearch(pageRequestDTO))
                    .orderBy(qPurchaser.purchaserCode.desc());

            // 페이지 처리
            List<Purchaser> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Purchaser, PurchaserDTO> fn = purchaser -> {
                PurchaserDTO dto  = purchaserMapper.toDto(purchaser);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getPurchaserSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        QPurchaser qPurchaser = QPurchaser.purchaser;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPurchaser.purchaserCode.contains("-"));  // 기본 조건

        if (type != null) {
            BooleanBuilder builder1 = new BooleanBuilder();

            if (type.contains("1")) {
                BooleanBuilder nameSearchBuilder = new BooleanBuilder();
                if (keyword != null) {
                    nameSearchBuilder.or(qPurchaser.purchaserName.contains(keyword));
                }
                builder1.and(nameSearchBuilder);
            }

            if (type.contains("2")) {
                builder1.or(qPurchaser.purchaserPresident.contains(keyword));
            }

            if (type.contains("3")) {
                builder1.or(qPurchaser.purchaserCode.contains(keyword));
            }

            builder.and(builder1);
        }

        return builder;
    }

    @Override
    public void savePurchaser(PurchaserDTO purchaserDTO) {
        purchaserRepository.save(purchaserMapper.toEntity(purchaserDTO));
    }

    @Override
    public List<PurchaserDTO> getPurchaserSearch(String keyword) {
        try {
            List<Purchaser> result = purchaserRepository.findByIdAndName(keyword);
            return purchaserMapper.toDtos(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
