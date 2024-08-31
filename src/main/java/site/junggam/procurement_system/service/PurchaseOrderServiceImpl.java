package site.junggam.procurement_system.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.PurchaseOrderMapper;
import site.junggam.procurement_system.repository.InspectionPlanRepository;
import site.junggam.procurement_system.repository.InventoryRepository;
import site.junggam.procurement_system.repository.PurchaseOrderRepository;
import site.junggam.procurement_system.repository.WarehousingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 //@NonNull or final만 생성자로 만든다.
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final InspectionPlanRepository inspectionPlanRepository;
    private final WarehousingRepository warehousingRepository;
    private final InventoryRepository inventoryRepository;


    private int getInspectionPlanCount(PurchaseOrder purchaseOrder) {
        List<InspectionPlan> inspectionPlans = inspectionPlanRepository.findByPurchaseOrder(purchaseOrder);
        long count = inspectionPlans.stream()
                .filter(plan -> !plan.getInspectionPlanDeliveryProgress().equals(InspectionPlanDeliveryProgress.NOT_INSPECTED))
                .count();
        return (int) count;
    }


    //CYH : 24.08.30 수정
    @Override
    @Transactional
    public PageResultDTO<PurchaseOrderDTO, PurchaseOrder> getPurchaseOrderList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrderStatus").descending()); //나주에 바꿀것
//            Page<PurchaseOrder> result = purchaseOrderRepository.findAll(pageable);
//            Function<PurchaseOrder, PurchaseOrderDTO> fn = (purchaseOrder -> {
//                PurchaseOrderDTO dto = purchaseOrderMapper.toDTO(purchaseOrder);
//                int inspectionPlanCount = getInspectionPlanCount(purchaseOrder);
//                dto.setInspectionPlanCount(inspectionPlanCount);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrderStatus").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
            QMaterial qMaterial = QMaterial.material;
            QPurchaser qPurchaser = QPurchaser.purchaser;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<PurchaseOrder> query = queryFactory.selectFrom(qPurchaseOrder)
                    .where(getPurchaseOrderSearch(pageRequestDTO))
                    .orderBy(qPurchaseOrder.purchaseOrderDate.desc());

            // 페이지 처리
            List<PurchaseOrder> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<PurchaseOrder, PurchaseOrderDTO> fn = purchaseOrder -> {
                PurchaseOrderDTO dto = purchaseOrderMapper.toDTO(purchaseOrder);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getPurchaseOrderSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        LocalDate startDate2 = pageRequestDTO.getStartDate2();
        LocalDate endDate2 = pageRequestDTO.getEndDate2();

        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
        QMaterial qMaterial = QMaterial.material;
        QPurchaser qPurchaser = QPurchaser.purchaser;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPurchaseOrder.purchaseOrderCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qPurchaseOrder.purchaseOrderDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }
        if (startDate2 != null && endDate2 != null) {
            builder.and(qPurchaseOrder.procurementPlan.procurementPlanDeadLine.between(startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder typeBuilder = new BooleanBuilder();

            // Ensure that qWarehousing.purchaseOrder.procurementPlan.material is not null
            if (type.contains("1")) {
                if (keyword != null && qPurchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qPurchaseOrder.procurementPlan.material.materialName.contains(keyword));
                }
            }

            if (type.contains("2")) {
                if (keyword != null && qPurchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qPurchaseOrder.procurementPlan.material.materialCode.contains(keyword));
                }
            }

            if (type.contains("3")) {
                if (keyword != null && qPurchaseOrder.procurementPlan.material.contract != null) {
                    typeBuilder.or(qPurchaseOrder.procurementPlan.material.contract.purchaser.purchaserName.contains(keyword));
                }
            }

            builder.and(typeBuilder);
        }

        return builder;
    }

    @Override
    @Transactional
    public PurchaseOrderDTO getPurchaseOrder(String purchaseOrderCode) {
        log.info("발주상세보기 서비스");
        Optional<PurchaseOrder> result=purchaseOrderRepository.findById(purchaseOrderCode);

        if (result.isPresent()) {
            PurchaseOrder purchaseOrder = result.get();

            // PurchaseOrder를 DTO로 변환
            PurchaseOrderDTO purchaseOrderDTO = purchaseOrderMapper.toDTO(purchaseOrder);
            int inspectionPlanCount = getInspectionPlanCount(purchaseOrder);
            purchaseOrderDTO.setInspectionPlanCount(inspectionPlanCount);

            return purchaseOrderDTO;
        } else {
            return null;
        }
    }


    @Override
    public void savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {

        String purchaseOrderCode=purchaseOrderDTO.getPurchaseOrderCode();
        //발주저장
        Optional<PurchaseOrder> result
                = purchaseOrderRepository.findById(purchaseOrderCode);
        if(result.isPresent()) {
            PurchaseOrder purchaseOrder = result.get();
            purchaseOrder.changePurchaseOrderDate(purchaseOrderDTO.getPurchaseOrderDate());
            purchaseOrder.changePurchaseOrderStatus(purchaseOrderDTO.getPurchaseOrderStatus());
            purchaseOrder.changePurchaseOrderMemo(purchaseOrderDTO.getPurchaseOrderMemo());
            purchaseOrderRepository.save(purchaseOrder);

            //입고대기 생성
            warehousingRepository.save(Warehousing.builder()
                    .warehousingCode("WARE"+purchaseOrderCode.substring(4))
                    .purchaseOrder(purchaseOrder)
                    .warehousingStatus(WarehousingStatus.PENDING)
                    .build());

            //인벤토리 입고예정수량 추가
            Inventory inventory= inventoryRepository.findById(purchaseOrder.getProcurementPlan().getMaterial().getMaterialCode()).get();
            inventory.setWarehousingPendingQuantity(inventory.getWarehousingPendingQuantity()+purchaseOrder.getProcurementPlan().getProcurementPlanQuantity());
            inventoryRepository.save(inventory);
        }

    }


}
