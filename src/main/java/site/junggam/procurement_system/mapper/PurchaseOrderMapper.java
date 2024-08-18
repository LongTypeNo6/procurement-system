package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    @Mapping(source = "procurementPlan.procurementPlanDeadLine", target = "procurementPlanDeadLine")
    @Mapping(source = "procurementPlan.procurementPlanQuantity", target = "procurementPlanQuantity")
    @Mapping(source = "procurementPlan.temMaterial.materialCode", target = "materialCode")
    @Mapping(source = "procurementPlan.temMaterial.materialName", target = "materialName")
    @Mapping(source = "procurementPlan.temMaterial.materialStand", target = "materialStand")
    @Mapping(source = "procurementPlan.temMaterial.materialTexture", target = "materialTexture")
    @Mapping(source = "procurementPlan.temMaterial.contract.contractPrice", target = "contractPrice")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserCode", target = "purchaserCode")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserName", target = "purchaserName")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserManager", target = "purchaserManager")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserManagerTel", target = "purchaserManagerTel")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserManagerEmail", target = "purchaserManagerEmail")
    @Mapping(source = "procurementPlan.temMaterial.contract.purchaser.purchaserManagerFax", target = "purchaserManagerFax")
    @Mapping(target = "inspectionPlanCount", ignore = true)
    PurchaseOrderDTO toDTO(PurchaseOrder purchaseOrder);

    List<PurchaseOrderDTO> toDTOs(List<PurchaseOrder> purchaseOrderList);

    @Mapping(target = "purchaseOrderStatus",defaultValue = "PENDING")
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);


}


