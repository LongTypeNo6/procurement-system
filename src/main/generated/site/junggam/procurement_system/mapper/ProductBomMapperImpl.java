package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProductBomDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.ProductBom;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-20T22:14:44+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProductBomMapperImpl implements ProductBomMapper {

    @Override
    public ProductBomDTO toDTO(ProductBom productBom) {
        if ( productBom == null ) {
            return null;
        }

        ProductBomDTO.ProductBomDTOBuilder productBomDTO = ProductBomDTO.builder();

        productBomDTO.productName( productBomProductProductName( productBom ) );
        productBomDTO.productCode( productBomProductProductCode( productBom ) );
        productBomDTO.id( productBom.getId() );
        productBomDTO.productBomQuantity( productBom.getProductBomQuantity() );
        productBomDTO.productBomProcess( productBom.getProductBomProcess() );

        return productBomDTO.build();
    }

    @Override
    public List<ProductBomDTO> toDTOs(List<ProductBom> productBoms) {
        if ( productBoms == null ) {
            return null;
        }

        List<ProductBomDTO> list = new ArrayList<ProductBomDTO>( productBoms.size() );
        for ( ProductBom productBom : productBoms ) {
            list.add( toDTO( productBom ) );
        }

        return list;
    }

    @Override
    public ProductBom toEntity(ProductBomDTO productBomDTO) {
        if ( productBomDTO == null ) {
            return null;
        }

        ProductBom.ProductBomBuilder productBom = ProductBom.builder();

        productBom.product( productBomDTOToProduct( productBomDTO ) );
        productBom.id( productBomDTO.getId() );
        productBom.productBomQuantity( productBomDTO.getProductBomQuantity() );
        productBom.productBomProcess( productBomDTO.getProductBomProcess() );

        return productBom.build();
    }

    private String productBomProductProductName(ProductBom productBom) {
        if ( productBom == null ) {
            return null;
        }
        Product product = productBom.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }

    private String productBomProductProductCode(ProductBom productBom) {
        if ( productBom == null ) {
            return null;
        }
        Product product = productBom.getProduct();
        if ( product == null ) {
            return null;
        }
        String productCode = product.getProductCode();
        if ( productCode == null ) {
            return null;
        }
        return productCode;
    }

    protected Product productBomDTOToProduct(ProductBomDTO productBomDTO) {
        if ( productBomDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productName( productBomDTO.getProductName() );
        product.productCode( productBomDTO.getProductCode() );

        return product.build();
    }
}
