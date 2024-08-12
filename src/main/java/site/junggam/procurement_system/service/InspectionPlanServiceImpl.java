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
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.mapper.InspectionPlanMapper;
import site.junggam.procurement_system.repository.InspectionPlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class InspectionPlanServiceImpl implements InspectionPlanService {

    private final InspectionPlanRepository inspectionPlanRepository;
    private final InspectionPlanMapper inspectionPlanMapper;

    private int getInspectionPlanCount(PurchaseOrder purchaseOrder) {
        List<InspectionPlan> inspectionPlans = inspectionPlanRepository.findByPurchaseOrder(purchaseOrder);
        int size = inspectionPlans.size();
        return size;
    }

    @Override
    @Transactional
    public List<InspectionPlanDTO> getInspectionPlan(String purchaseOrderCode) {
        List<InspectionPlan> result = inspectionPlanRepository.findByPurchaseOrder(PurchaseOrder.builder().purchaseOrderCode(purchaseOrderCode).build());
        return inspectionPlanMapper.toDTOs(result);
    }

    @Override
    public void saveInspectionPlan(InspectionPlanDTO inspectionPlanDTO) {
        inspectionPlanRepository.save(inspectionPlanMapper.toEntity(inspectionPlanDTO));
    }

    @Override
    public PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlanList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("PurchaseOrder.purchaseOrderCode").descending());
            Page<InspectionPlan> result = inspectionPlanRepository.findAll(pageable);

            Function<InspectionPlan, InspectionPlanDTO> fn = (inspectionPlan -> {
                InspectionPlanDTO dto =inspectionPlanMapper.toDTO(inspectionPlan);
                int inspectionPlanCount = getInspectionPlanCount(inspectionPlan.getPurchaseOrder());
                dto.setInspectionPlanCount(inspectionPlanCount);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
