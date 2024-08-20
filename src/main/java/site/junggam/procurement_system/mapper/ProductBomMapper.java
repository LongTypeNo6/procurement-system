package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProductBomDTO;
import site.junggam.procurement_system.entity.ProductBom;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UnitBomMapper.class})
public interface ProductBomMapper {

    ProductBomMapper INSTANCE = Mappers.getMapper(ProductBomMapper.class);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.productCode", target = "productCode")
    ProductBomDTO toDTO(ProductBom productBom);

    List<ProductBomDTO> toDTOs(List<ProductBom> productBoms);

    @Mapping(source = "productName", target = "product.productName")
    @Mapping(source = "productCode", target = "product.productCode")
    ProductBom toEntity(ProductBomDTO productBomDTO);

    // Additional manual mapping method
    default ProductBomDTO toDTOWithUnits(ProductBom productBom, List<UnitBom> unitBoms) {
        ProductBomDTO productBomDTO = toDTO(productBom);
        productBomDTO.setUnitBomDTOList(
                UnitBomMapper.INSTANCE.toDTOs(unitBoms)
        );
        return productBomDTO;
    }
}

