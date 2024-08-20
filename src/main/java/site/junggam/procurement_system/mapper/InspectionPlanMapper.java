package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.entity.InspectionPlan;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InspectionPlanMapper {
    InspectionPlanMapper INSTANCE = Mappers.getMapper(InspectionPlanMapper.class);

    @Mapping(target = "inspectionPlanCount", ignore = true)
    @Mapping(target = "previousInspectionPlanProgress", ignore = true)
    @Mapping(source = "purchaseOrder.procurementPlan.procurementPlanDeadLine", target = "purchaseOrderDTO.procurementPlanDeadLine")
    @Mapping(source = "purchaseOrder.procurementPlan.procurementPlanQuantity", target = "purchaseOrderDTO.procurementPlanQuantity")
    @Mapping(source = "purchaseOrder.procurementPlan.material.materialCode", target = "purchaseOrderDTO.materialCode")
    @Mapping(source = "purchaseOrder.procurementPlan.material.materialName", target = "purchaseOrderDTO.materialName")
    @Mapping(source = "purchaseOrder.procurementPlan.material.materialStand", target = "purchaseOrderDTO.materialStand")
    @Mapping(source = "purchaseOrder.procurementPlan.material.materialTexture", target = "purchaseOrderDTO.materialTexture")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.contractPrice", target = "purchaseOrderDTO.contractPrice")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserCode", target = "purchaseOrderDTO.purchaserCode")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserName", target = "purchaseOrderDTO.purchaserName")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserManager", target = "purchaseOrderDTO.purchaserManager")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserManagerTel", target = "purchaseOrderDTO.purchaserManagerTel")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserManagerEmail", target = "purchaseOrderDTO.purchaserManagerEmail")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserManagerFax", target = "purchaseOrderDTO.purchaserManagerFax")
    @Mapping(source = "purchaseOrder.procurementPlan.material.contract.purchaser.purchaserAddress", target = "purchaserAddress")
    InspectionPlanDTO toDTO(InspectionPlan inspectionPlan);

    @Mapping(target = "inspectionPlanCount", ignore = true)
    @Mapping(target = "previousInspectionPlanProgress", ignore = true)
    @Mapping(source = "purchaseOrder", target = "purchaseOrderDTO")
    List<InspectionPlanDTO> toDTOs(List<InspectionPlan> inspectionPlans);

    @Mapping(source = "purchaseOrderDTO", target = "purchaseOrder")
    InspectionPlan toEntity(InspectionPlanDTO inspectionPlanDTO);
}
