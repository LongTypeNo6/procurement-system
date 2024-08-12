package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.InspectionPlan;

import java.util.List;
public interface InspectionPlanService {

    //하나의 발주에 대한 검수계획 리스트 보기
    List<InspectionPlanDTO> getInspectionPlan(String purchaseOrderCode);

    //검수계획등록
    void saveInspectionPlan(InspectionPlanDTO inspectionPlanDTO);


    //모든 검수계획리스트 보기
    PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlanList(PageRequestDTO pageRequestDTO);
}
