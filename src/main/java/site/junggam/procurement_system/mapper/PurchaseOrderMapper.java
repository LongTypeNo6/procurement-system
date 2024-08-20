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
    @Mapping(source = "procurementPlan.material.materialCode", target = "materialCode")
    @Mapping(source = "procurementPlan.material.materialName", target = "materialName")
    @Mapping(source = "procurementPlan.material.materialStand", target = "materialStand")
    @Mapping(source = "procurementPlan.material.materialTexture", target = "materialTexture")
    @Mapping(source = "procurementPlan.material.contract.contractPrice", target = "contractPrice")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserCode", target = "purchaserCode")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserName", target = "purchaserName")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserManager", target = "purchaserManager")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserManagerTel", target = "purchaserManagerTel")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserManagerEmail", target = "purchaserManagerEmail")
    @Mapping(source = "procurementPlan.material.contract.purchaser.purchaserManagerFax", target = "purchaserManagerFax")
    @Mapping(target = "inspectionPlanCount", ignore = true)
    PurchaseOrderDTO toDTO(PurchaseOrder purchaseOrder);

    List<PurchaseOrderDTO> toDTOs(List<PurchaseOrder> purchaseOrderList);

    @Mapping(target = "purchaseOrderStatus",defaultValue = "PENDING")
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);


}


