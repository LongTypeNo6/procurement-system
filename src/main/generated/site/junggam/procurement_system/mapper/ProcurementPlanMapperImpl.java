package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProcurementPlanDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ProductionPlan;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T06:38:51+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ProcurementPlanMapperImpl implements ProcurementPlanMapper {

    @Override
    public ProcurementPlanDTO toDTO(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }

        ProcurementPlanDTO.ProcurementPlanDTOBuilder procurementPlanDTO = ProcurementPlanDTO.builder();

        procurementPlanDTO.materialName( procurementPlanMaterialMaterialName( procurementPlan ) );
        procurementPlanDTO.materialCode( procurementPlanMaterialMaterialCode( procurementPlan ) );
        procurementPlanDTO.productionPlanCode( procurementPlanProductionPlanProductionPlanCode( procurementPlan ) );
        procurementPlanDTO.procurementPlanCode( procurementPlan.getProcurementPlanCode() );
        procurementPlanDTO.procurementPlantRegDate( procurementPlan.getProcurementPlantRegDate() );
        procurementPlanDTO.procurementPlanDeadLine( procurementPlan.getProcurementPlanDeadLine() );
        procurementPlanDTO.procurementPlanQuantity( procurementPlan.getProcurementPlanQuantity() );

        return procurementPlanDTO.build();
    }

    @Override
    public ProcurementPlan toEntity(ProcurementPlanDTO procurementPlanDTO) {
        if ( procurementPlanDTO == null ) {
            return null;
        }

        ProcurementPlan.ProcurementPlanBuilder procurementPlan = ProcurementPlan.builder();

        procurementPlan.material( procurementPlanDTOToMaterial( procurementPlanDTO ) );
        procurementPlan.procurementPlanCode( procurementPlanDTO.getProcurementPlanCode() );
        procurementPlan.procurementPlantRegDate( procurementPlanDTO.getProcurementPlantRegDate() );
        procurementPlan.procurementPlanDeadLine( procurementPlanDTO.getProcurementPlanDeadLine() );
        procurementPlan.procurementPlanQuantity( procurementPlanDTO.getProcurementPlanQuantity() );

        return procurementPlan.build();
    }

    private String procurementPlanMaterialMaterialName(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialName = material.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String procurementPlanMaterialMaterialCode(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialCode = material.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String procurementPlanProductionPlanProductionPlanCode(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        ProductionPlan productionPlan = procurementPlan.getProductionPlan();
        if ( productionPlan == null ) {
            return null;
        }
        String productionPlanCode = productionPlan.getProductionPlanCode();
        if ( productionPlanCode == null ) {
            return null;
        }
        return productionPlanCode;
    }

    protected Material procurementPlanDTOToMaterial(ProcurementPlanDTO procurementPlanDTO) {
        if ( procurementPlanDTO == null ) {
            return null;
        }

        Material.MaterialBuilder material = Material.builder();

        material.materialName( procurementPlanDTO.getMaterialName() );
        material.materialCode( procurementPlanDTO.getMaterialCode() );

        return material.build();
    }
}
