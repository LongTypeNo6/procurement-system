package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProcurementPlanDTO;
import site.junggam.procurement_system.entity.ProcurementPlan;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProcurementPlanMapper {
    ProcurementPlanMapper INSTANCE = Mappers.getMapper(ProcurementPlanMapper.class);

    @Mapping(source = "material.contract.contractPrice", target = "contractPrice")
    @Mapping(source = "material.contract.contractLeadTime", target = "contractLeadTime")
    @Mapping(source = "material.contract.contractMemo", target = "contractMemo")
    @Mapping(source = "material.estimate.estimateMemo", target = "estimateMemo")
    @Mapping(source = "material.contract.contractCode", target = "contractCode")
    @Mapping(source = "material.estimate.estimateCode", target = "estimateCode")
    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "material.materialCode", target = "materialCode")
    @Mapping(source = "material.contract.contractStatus", target = "contractStatus")
    @Mapping(source = "productionPlan.productionPlanCode", target = "productionPlanCode")
    @Mapping(source = "productionPlan.productionPlanDeadLine", target = "productionPlanDeadLine")
    @Mapping(source = "material.inventory.availableQuantity", target ="availableQuantity")
    ProcurementPlanDTO toDTO(ProcurementPlan procurementPlan);

    List<ProcurementPlanDTO> toDTOs(List<ProcurementPlan> procurementPlans);

    @Mapping(source = "materialName", target = "material.materialName")
    @Mapping(source = "materialCode", target = "material.materialCode")
    @Mapping(source = "productionPlanCode", target = "productionPlan.productionPlanCode")
    ProcurementPlan toEntity(ProcurementPlanDTO procurementPlanDTO);
}
