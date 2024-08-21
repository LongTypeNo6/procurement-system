package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProductBomDTO;
import site.junggam.procurement_system.entity.ProductBom;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductBomMapper {

    ProductBomMapper INSTANCE = Mappers.getMapper(ProductBomMapper.class);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.productCode", target = "productCode")
    @Mapping(source = "unit.unitName", target = "unitName")
    @Mapping(source = "unit.unitCode", target = "unitCode")
    ProductBomDTO toDTO(ProductBom productBom);

    List<ProductBomDTO> toDTOs(List<ProductBom> productBoms);

    @Mapping(source = "productCode", target = "product.productCode")
    @Mapping(source = "unitCode", target = "unit.unitCode")
    ProductBom toEntity(ProductBomDTO productBomDTO);


}

