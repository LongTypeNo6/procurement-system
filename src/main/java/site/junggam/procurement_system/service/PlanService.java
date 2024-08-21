package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ProductionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.util.List;

public interface PlanService {

    //제품 검색(출고요청용)
    List<ProductDTO> getProductListSearching(String keyword);

    //유닛 검색(출고요청용)
    List<UnitDTO> getUnitListSearching(String keyword);

    //자재 검색(출고요청용)
    List<MaterialDTO> getMaterialListSearching(String keyword);

    //유닛코드로 유닛BOM리스트 불러오기
    List<UnitBomDTO> getUnitBomList(String unitCode);

    //프로덕트코드로 유닛BOM리스트 불러오기
    List<UnitBomDTO> getUnitBomListWithProductCode(String productCode);

    //프로덕트코드로 프로덕트BOM리스트 불러오기
    List<ProductBomDTO> getProductBomList(String productCode);

    //생산계획추가
    void insertProductionPlan(ProductionPlanDTO productionPlanDTO);

    //생산계획리스트보기
    PageResultDTO<ProductionPlanDTO, ProductionPlan> getProductionPlanList(PageRequestDTO pageRequestDTO);

    //생산계획 상세보기
    ProductionPlanDTO getProductionPlan(String productionPlanCode);

    //조달계획리스트 보기
    PageResultDTO<ProcurementPlanDTO, ProcurementPlan> getProcurementPlanList(PageRequestDTO pageRequestDTO);

    //조달계획 상세보기
    ProcurementPlanDTO getProcurementPlan(String procurementPlanCode);

    //견적추가
    String resisterEstimate(EstimateDTO estimateDTO);

    //계약추가
    String resisterContract(ContractDTO contractDTO);
}
