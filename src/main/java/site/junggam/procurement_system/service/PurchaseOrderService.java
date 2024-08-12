package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    //1. 발주서 목록보기
    PageResultDTO<PurchaseOrderDTO, PurchaseOrder> getPurchaseOrderList(PageRequestDTO pageRequestDTO);

    //2-1 발주 상세보기
    PurchaseOrderDTO getPurchaseOrder(String purchaseOrderCode);

    //2-2 발주 처리
    void savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);




//엔티티랑 디티오처리 MapStruct로 대신함
    
//    //이하 엔티티랑 디티오처리
//
//    //DTO->Entity
//    default PurchaseOrder dtotoEntity(PurchaseOrderDTO dto){
//        PurchaseOrder purchaseOrder
//                = PurchaseOrder.builder()
//                            .purchaseOrderCode(dto.getPurchaseOrderCode())
//                            .purchaseOrderDate(dto.getPurchaseOrderDate())
//                            .purchaseOrderMemo(dto.getPurchaseOrderMemo())
//                            .procurementPlan(ProcurementPlan.builder().procurementPlanCode(dto.getProcurementPlanDTO().getProcurementPlanCode()).build())
//                            .build();
//        return purchaseOrder;
//    }
//    //Entity->DTO
//    default PurchaseOrderDTO entitytoDTO(PurchaseOrder entity){
//        PurchaseOrderDTO purchaseOrderDTO
//                = PurchaseOrderDTO.builder()
//                .purchaseOrderCode(entity.getPurchaseOrderCode())
//                .purchaseOrderDate(entity.getPurchaseOrderDate())
//                .purchaseOrderMemo(entity.getPurchaseOrderMemo())
//                .procurementPlanDTO(ProcurementPlanDTO.builder()
//                        .procurementPlanCode(entity.getProcurementPlan().getProcurementPlanCode())
//                        .procurementPlanDeadLine(entity.getProcurementPlan().getProcurementPlanDeadLine())
//                        .procurementPlanQuantity(entity.getProcurementPlan().getProcurementPlanQuantity())
//                        .procurementPlantRegDate(entity.getProcurementPlan().getProcurementPlantRegDate())
//                        .temMaterialDTO(TemMaterialDTO.builder()
//                                .materialCode(entity.getProcurementPlan().getTemMaterial().getMaterialCode())
//                                .materialName(entity.getProcurementPlan().getTemMaterial().getMaterialName())
//                                .materialStand(entity.getProcurementPlan().getTemMaterial().getMaterialStand())
//                                .materialTexture(entity.getProcurementPlan().getTemMaterial().getMaterialTexture())
//                                .materialDrawFile(entity.getProcurementPlan().getTemMaterial().getMaterialDrawFile())
//                                .materialEtcFile(entity.getProcurementPlan().getTemMaterial().getMaterialEtcFile())
//                                .materialRegDate(entity.getProcurementPlan().getTemMaterial().getMaterialRegDate())
//                                .materialModDate(entity.getProcurementPlan().getTemMaterial().getMaterialModDate())
//                                .materialSafeQuantity(entity.getProcurementPlan().getTemMaterial().getMaterialSafeQuantity())
//                                .build())
//                        .build())
//                .build();
//        return purchaseOrderDTO;
//    }
}
