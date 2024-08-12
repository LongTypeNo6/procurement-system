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
    @Mapping(source = "purchaseOrder", target = "purchaseOrderDTO")
    InspectionPlanDTO toDTO(InspectionPlan inspectionPlan);

    @Mapping(target = "inspectionPlanCount", ignore = true)
    @Mapping(source = "purchaseOrder", target = "purchaseOrderDTO")
    List<InspectionPlanDTO> toDTOs(List<InspectionPlan> inspectionPlans);

    @Mapping(source = "purchaseOrderDTO", target = "purchaseOrder")
    InspectionPlan toEntity(InspectionPlanDTO inspectionPlanDTO);
}
