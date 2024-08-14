package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.util.List;
public interface InspectionPlanService {
    
    //하나의 발주에 대한 검수계획 개수보기
    public int getInspectionPlanCount(PurchaseOrder purchaseOrder);
    
    //하나의 발주에 대한 검수계획 리스트 보기
    List<InspectionPlanDTO> getInspectionPlan(String purchaseOrderCode);

    //검수계획등록
    void saveInspectionPlan(InspectionPlanDTO inspectionPlanDTO);

    //검수계획 수정
    void modifyInspectionPlan(InspectionPlanDTO inspectionPlanDTO);

    //검수결과 등록
    void saveInspectionResult(InspectionPlanDTO inspectionPlanDTO);

    //모든 검수계획리스트 보기
    PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlanList(PageRequestDTO pageRequestDTO);

    //모둔검수일정리스트 보기
    PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlaScheduleList(PageRequestDTO pageRequestDTO);
}
