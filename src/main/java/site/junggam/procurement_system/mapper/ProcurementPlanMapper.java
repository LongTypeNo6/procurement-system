package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProcurementPlanDTO;
import site.junggam.procurement_system.entity.ProcurementPlan;

@Mapper(componentModel = "spring")
public interface ProcurementPlanMapper {
    ProcurementPlanMapper INSTANCE = Mappers.getMapper(ProcurementPlanMapper.class);


    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "material.materialCode", target = "materialCode")
    @Mapping(source = "productionPlan.productionPlanCode", target = "productionPlanCode")
    ProcurementPlanDTO toDTO(ProcurementPlan procurementPlan);

    ProcurementPlan toEntity(ProcurementPlanDTO procurementPlanDTO);
}
