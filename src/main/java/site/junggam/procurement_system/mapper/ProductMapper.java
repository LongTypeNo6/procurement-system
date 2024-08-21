package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.entity.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTOs(List<Product> products);

    Product toEntity(ProductDTO productDTO);
}
