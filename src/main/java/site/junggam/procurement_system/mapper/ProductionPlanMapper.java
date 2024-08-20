package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProductionPlanDTO;
import site.junggam.procurement_system.entity.ProductionPlan;

@Mapper(componentModel = "spring")
public interface ProductionPlanMapper {
    ProductionPlanMapper INSTANCE = Mappers.getMapper(ProductionPlanMapper.class);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.productCode", target = "productCode")
    @Mapping(source = "unit.unitName", target = "unitName")
    @Mapping(source = "unit.unitCode", target = "unitCode")
    ProductionPlanDTO toDTO (ProductionPlan productionPlan);

    ProductionPlan toEntity (ProductionPlanDTO productionPlanDTO);
}
