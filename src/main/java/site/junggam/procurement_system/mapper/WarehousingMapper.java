package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.Warehousing;

@Mapper(componentModel = "spring")
public interface WarehousingMapper {

    WarehousingMapper INSTANCE = Mappers.getMapper(WarehousingMapper.class);

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
    WarehousingDTO toDTO(Warehousing warehousing);
}
