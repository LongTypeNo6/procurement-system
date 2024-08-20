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
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.InspectionPlanDeliveryProgress;
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

    @Override
    public int getInspectionPlanCount(PurchaseOrder purchaseOrder) {
        List<InspectionPlan> inspectionPlans = inspectionPlanRepository.findByPurchaseOrder(purchaseOrder);
        long count = inspectionPlans.stream()
                .filter(plan -> !plan.getInspectionPlanDeliveryProgress().equals(InspectionPlanDeliveryProgress.NOT_INSPECTED))
                .count();
        return (int) count;
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
    public void modifyInspectionPlan(InspectionPlanDTO inspectionPlanDTO) {
        Optional<InspectionPlan> result
                = inspectionPlanRepository.findById(inspectionPlanDTO.getInspectionPlanCode());
        if (result.isPresent()) {
            InspectionPlan inspectionPlan = result.get();
            inspectionPlan.changeInspectionPlanDateTime(inspectionPlanDTO.getInspectionPlanDateTime());
            inspectionPlan.changeInspectionPlanMemo(inspectionPlanDTO.getInspectionPlanMemo());
            inspectionPlanRepository.save(inspectionPlan);
        }
    }

    @Override
    public void saveInspectionResult(InspectionPlanDTO inspectionPlanDTO) {
        Optional<InspectionPlan> result
                = inspectionPlanRepository.findById(inspectionPlanDTO.getInspectionPlanCode());
        if (result.isPresent()) {
            InspectionPlan inspectionPlan = result.get();
            inspectionPlan.changeInspectionResultDateTime(inspectionPlanDTO.getInspectionResultDateTime());
            inspectionPlan.changeInspectionPlanProgress(inspectionPlanDTO.getInspectionPlanProgress());
            inspectionPlan.changeInspectionPlanStatus(inspectionPlanDTO.getInspectionPlanStatus());
            inspectionPlan.changeInspectionPlanComplementary(inspectionPlanDTO.getInspectionPlanComplementary());
            inspectionPlan.changeInspectionPlanDeliveryProgress(inspectionPlanDTO.getInspectionPlanDeliveryProgress());
            inspectionPlanRepository.save(inspectionPlan);
        }
    }

    @Override
    public PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlanList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("PurchaseOrder.purchaseOrderCode").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게
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

    private int preInspectionPlanProgress(String inspectionPlanCode){
        String codeExcludingNum=inspectionPlanCode.substring(0,inspectionPlanCode.length()-1);
        int preNum=Integer.parseInt(inspectionPlanCode.substring(inspectionPlanCode.length()-1))-1;
        if(preNum==0){
            return 0;
        }else {
            String preInspectionPlanCode=codeExcludingNum+preNum;
            return inspectionPlanRepository.findById(preInspectionPlanCode).get().getInspectionPlanProgress();
        }
    }


    @Override
    public PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlaScheduleList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("inspectionPlanDateTime").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게
            Page<InspectionPlan> result = inspectionPlanRepository.findAll(pageable);

            Function<InspectionPlan, InspectionPlanDTO> fn = (inspectionPlan -> {
                InspectionPlanDTO dto =inspectionPlanMapper.toDTO(inspectionPlan);
                int inspectionPlanCount = getInspectionPlanCount(inspectionPlan.getPurchaseOrder());
                dto.setInspectionPlanCount(inspectionPlanCount);
                dto.setPreviousInspectionPlanProgress(preInspectionPlanProgress(inspectionPlan.getInspectionPlanCode()));
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
