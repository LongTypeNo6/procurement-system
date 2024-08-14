package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.InspectionPlanDeliveryProgress;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.mapper.PurchaseOrderMapper;
import site.junggam.procurement_system.repository.InspectionPlanRepository;
import site.junggam.procurement_system.repository.PurchaseOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 //@NonNull or final만 생성자로 만든다.
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final InspectionPlanRepository inspectionPlanRepository;


    private int getInspectionPlanCount(PurchaseOrder purchaseOrder) {
        List<InspectionPlan> inspectionPlans = inspectionPlanRepository.findByPurchaseOrder(purchaseOrder);
        long count = inspectionPlans.stream()
                .filter(plan -> !plan.getInspectionPlanDeliveryProgress().equals(InspectionPlanDeliveryProgress.NOT_INSPECTED))
                .count();
        return (int) count;
    }


    @Override
    @Transactional
    public PageResultDTO<PurchaseOrderDTO, PurchaseOrder> getPurchaseOrderList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrderDate").descending()); //나주에 바꿀것
            Page<PurchaseOrder> result = purchaseOrderRepository.findAll(pageable);
            Function<PurchaseOrder, PurchaseOrderDTO> fn = (purchaseOrder -> {
                PurchaseOrderDTO dto = purchaseOrderMapper.toDTO(purchaseOrder);
                int inspectionPlanCount = getInspectionPlanCount(purchaseOrder);
                dto.setInspectionPlanCount(inspectionPlanCount);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
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
        Optional<PurchaseOrder> result
                = purchaseOrderRepository.findById(purchaseOrderDTO.getPurchaseOrderCode());
        if(result.isPresent()) {
            PurchaseOrder purchaseOrder = result.get();
            purchaseOrder.changePurchaseOrderDate(purchaseOrderDTO.getPurchaseOrderDate());
            purchaseOrder.changePurchaseOrderStatus(purchaseOrderDTO.getPurchaseOrderStatus());
            purchaseOrder.changePurchaseOrderMemo(purchaseOrderDTO.getPurchaseOrderMemo());
            purchaseOrderRepository.save(purchaseOrder);
        }

    }


}
