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
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.InventoryMapper;
import site.junggam.procurement_system.repository.InventoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<InventoryDTO, Inventory> getInventoryList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나주에 바꿀것
//            Page<Inventory> result = inventoryRepository.findAll(pageable);
//            Function<Inventory, InventoryDTO> fn = (inventory -> {
//                InventoryDTO dto = inventoryMapper.toDTO(inventory);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QInventory qInventory = QInventory.inventory;
            QMaterial qMaterial = QMaterial.material;
            QPurchaser qPurchaser = QPurchaser.purchaser;
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Inventory> query = queryFactory.selectFrom(qInventory)
                    .where(getInventorySearch(pageRequestDTO))
                    .orderBy(qInventory.materialCode.desc());

            // 페이지 처리
            List<Inventory> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Inventory, InventoryDTO> fn = inventory -> {
               InventoryDTO dto = inventoryMapper.toDTO(inventory);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getInventorySearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        LocalDate startDate2 = pageRequestDTO.getStartDate2();
        LocalDate endDate2 = pageRequestDTO.getEndDate2();

        QInventory qInventory = QInventory.inventory;
        QMaterial qMaterial = QMaterial.material;
        QPurchaser qPurchaser = QPurchaser.purchaser;
        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInventory.materialCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qPurchaseOrder.purchaseOrderDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }
        if (startDate2 != null && endDate2 != null) {
            builder.and(qProcurementPlan.procurementPlanDeadLine.between(startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder builder1 = new BooleanBuilder();

            if (type.contains("1")) {
                BooleanBuilder nameSearchBuilder = new BooleanBuilder();
                if (keyword != null) {
                    nameSearchBuilder.or(qMaterial.materialName.contains(keyword));
                }
                builder1.and(nameSearchBuilder);
            }

            if (type.contains("2")) {
                builder1.or(qMaterial.materialCode.contains(keyword));
            }

            if (type.contains("3")) {
                builder1.or(qMaterial.materialStand.contains(keyword));
                builder1.or(qMaterial.materialTexture.contains(keyword));
            }

            builder.and(builder1);
        }

        return null;
    }

    @Override
    public Integer getTotallMaterialQuantity() {
        return inventoryRepository.getTotallMaterialQuantity();
    }

    @Override
    public Double getTotallMaterialPrice() {
        return inventoryRepository.getTotallContractAvgPrice()*inventoryRepository.getTotallMaterialQuantity();
    }
}
