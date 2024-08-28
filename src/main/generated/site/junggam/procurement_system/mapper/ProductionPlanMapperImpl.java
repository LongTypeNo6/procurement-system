package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProductionPlanDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.ProductionPlan;
import site.junggam.procurement_system.entity.Unit;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T14:55:51+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProductionPlanMapperImpl implements ProductionPlanMapper {

    @Override
    public ProductionPlanDTO toDTO(ProductionPlan productionPlan) {
        if ( productionPlan == null ) {
            return null;
        }

        ProductionPlanDTO.ProductionPlanDTOBuilder productionPlanDTO = ProductionPlanDTO.builder();

        productionPlanDTO.productName( productionPlanProductProductName( productionPlan ) );
        productionPlanDTO.productCode( productionPlanProductProductCode( productionPlan ) );
        productionPlanDTO.unitName( productionPlanUnitUnitName( productionPlan ) );
        productionPlanDTO.unitCode( productionPlanUnitUnitCode( productionPlan ) );
        productionPlanDTO.productionPlanCode( productionPlan.getProductionPlanCode() );
        productionPlanDTO.productionPlanRegDate( productionPlan.getProductionPlanRegDate() );
        productionPlanDTO.productionPlanDate( productionPlan.getProductionPlanDate() );
        productionPlanDTO.productionPlanDeadLine( productionPlan.getProductionPlanDeadLine() );
        productionPlanDTO.productionPlanQuantity( productionPlan.getProductionPlanQuantity() );

        return productionPlanDTO.build();
    }

    @Override
    public List<ProductionPlanDTO> toDTOs(List<ProductionPlan> productionPlans) {
        if ( productionPlans == null ) {
            return null;
        }

        List<ProductionPlanDTO> list = new ArrayList<ProductionPlanDTO>( productionPlans.size() );
        for ( ProductionPlan productionPlan : productionPlans ) {
            list.add( toDTO( productionPlan ) );
        }

        return list;
    }

    @Override
    public ProductionPlan toEntity(ProductionPlanDTO productionPlanDTO) {
        if ( productionPlanDTO == null ) {
            return null;
        }

        ProductionPlan.ProductionPlanBuilder productionPlan = ProductionPlan.builder();

        productionPlan.productionPlanCode( productionPlanDTO.getProductionPlanCode() );
        productionPlan.productionPlanRegDate( productionPlanDTO.getProductionPlanRegDate() );
        productionPlan.productionPlanDate( productionPlanDTO.getProductionPlanDate() );
        productionPlan.productionPlanDeadLine( productionPlanDTO.getProductionPlanDeadLine() );
        productionPlan.productionPlanQuantity( productionPlanDTO.getProductionPlanQuantity() );

        return productionPlan.build();
    }

    private String productionPlanProductProductName(ProductionPlan productionPlan) {
        if ( productionPlan == null ) {
            return null;
        }
        Product product = productionPlan.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }

    private String productionPlanProductProductCode(ProductionPlan productionPlan) {
        if ( productionPlan == null ) {
            return null;
        }
        Product product = productionPlan.getProduct();
        if ( product == null ) {
            return null;
        }
        String productCode = product.getProductCode();
        if ( productCode == null ) {
            return null;
        }
        return productCode;
    }

    private String productionPlanUnitUnitName(ProductionPlan productionPlan) {
        if ( productionPlan == null ) {
            return null;
        }
        Unit unit = productionPlan.getUnit();
        if ( unit == null ) {
            return null;
        }
        String unitName = unit.getUnitName();
        if ( unitName == null ) {
            return null;
        }
        return unitName;
    }

    private String productionPlanUnitUnitCode(ProductionPlan productionPlan) {
        if ( productionPlan == null ) {
            return null;
        }
        Unit unit = productionPlan.getUnit();
        if ( unit == null ) {
            return null;
        }
        String unitCode = unit.getUnitCode();
        if ( unitCode == null ) {
            return null;
        }
        return unitCode;
    }
}
