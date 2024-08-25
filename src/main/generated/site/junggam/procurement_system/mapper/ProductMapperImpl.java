package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.ProductUnitDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.ProductUnit;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T21:08:00+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.productCode( product.getProductCode() );
        productDTO.productName( product.getProductName() );
        productDTO.productPrice( product.getProductPrice() );
        productDTO.productStand( product.getProductStand() );
        productDTO.productTexture( product.getProductTexture() );
        productDTO.productDrawFile( product.getProductDrawFile() );
        productDTO.productEtcFile( product.getProductEtcFile() );
        productDTO.productRegDate( product.getProductRegDate() );
        productDTO.productModDate( product.getProductModDate() );
        productDTO.productUnits( productUnitSetToProductUnitDTOSet( product.getProductUnits() ) );

        return productDTO.build();
    }

    @Override
    public List<ProductDTO> toDTOs(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( toDTO( product ) );
        }

        return list;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productCode( productDTO.getProductCode() );
        product.productName( productDTO.getProductName() );
        product.productPrice( productDTO.getProductPrice() );
        product.productStand( productDTO.getProductStand() );
        product.productTexture( productDTO.getProductTexture() );
        product.productDrawFile( productDTO.getProductDrawFile() );
        product.productEtcFile( productDTO.getProductEtcFile() );
        product.productRegDate( productDTO.getProductRegDate() );
        product.productModDate( productDTO.getProductModDate() );
        product.productUnits( productUnitDTOSetToProductUnitSet( productDTO.getProductUnits() ) );

        return product.build();
    }

    protected ProductUnitDTO productUnitToProductUnitDTO(ProductUnit productUnit) {
        if ( productUnit == null ) {
            return null;
        }

        ProductUnitDTO.ProductUnitDTOBuilder productUnitDTO = ProductUnitDTO.builder();

        return productUnitDTO.build();
    }

    protected Set<ProductUnitDTO> productUnitSetToProductUnitDTOSet(Set<ProductUnit> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductUnitDTO> set1 = new LinkedHashSet<ProductUnitDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProductUnit productUnit : set ) {
            set1.add( productUnitToProductUnitDTO( productUnit ) );
        }

        return set1;
    }

    protected ProductUnit productUnitDTOToProductUnit(ProductUnitDTO productUnitDTO) {
        if ( productUnitDTO == null ) {
            return null;
        }

        ProductUnit.ProductUnitBuilder productUnit = ProductUnit.builder();

        return productUnit.build();
    }

    protected Set<ProductUnit> productUnitDTOSetToProductUnitSet(Set<ProductUnitDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductUnit> set1 = new LinkedHashSet<ProductUnit>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ProductUnitDTO productUnitDTO : set ) {
            set1.add( productUnitDTOToProductUnit( productUnitDTO ) );
        }

        return set1;
    }
}
