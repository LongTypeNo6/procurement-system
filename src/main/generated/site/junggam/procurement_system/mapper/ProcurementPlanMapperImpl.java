package site.junggam.procurement_system.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProcurementPlanDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.ContractStatus;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ProductionPlan;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T18:12:38+0900",
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
        procurementPlanDTO.contractStatus( procurementPlanMaterialContractContractStatus( procurementPlan ) );
        procurementPlanDTO.productionPlanCode( procurementPlanProductionPlanProductionPlanCode( procurementPlan ) );
        procurementPlanDTO.productionPlanDeadLine( procurementPlanProductionPlanProductionPlanDeadLine( procurementPlan ) );
        procurementPlanDTO.availableQuantity( procurementPlanMaterialInventoryAvailableQuantity( procurementPlan ) );
        procurementPlanDTO.procurementPlanCode( procurementPlan.getProcurementPlanCode() );
        procurementPlanDTO.procurementPlanRegDate( procurementPlan.getProcurementPlanRegDate() );
        procurementPlanDTO.procurementPlanDeadLine( procurementPlan.getProcurementPlanDeadLine() );
        procurementPlanDTO.procurementPlanQuantity( procurementPlan.getProcurementPlanQuantity() );
        procurementPlanDTO.procurementPlanStatus( procurementPlan.getProcurementPlanStatus() );

        return procurementPlanDTO.build();
    }

    @Override
    public List<ProcurementPlanDTO> toDTOs(List<ProcurementPlan> procurementPlans) {
        if ( procurementPlans == null ) {
            return null;
        }

        List<ProcurementPlanDTO> list = new ArrayList<ProcurementPlanDTO>( procurementPlans.size() );
        for ( ProcurementPlan procurementPlan : procurementPlans ) {
            list.add( toDTO( procurementPlan ) );
        }

        return list;
    }

    @Override
    public ProcurementPlan toEntity(ProcurementPlanDTO procurementPlanDTO) {
        if ( procurementPlanDTO == null ) {
            return null;
        }

        ProcurementPlan.ProcurementPlanBuilder procurementPlan = ProcurementPlan.builder();

        procurementPlan.material( procurementPlanDTOToMaterial( procurementPlanDTO ) );
        procurementPlan.productionPlan( procurementPlanDTOToProductionPlan( procurementPlanDTO ) );
        procurementPlan.procurementPlanCode( procurementPlanDTO.getProcurementPlanCode() );
        procurementPlan.procurementPlanRegDate( procurementPlanDTO.getProcurementPlanRegDate() );
        procurementPlan.procurementPlanDeadLine( procurementPlanDTO.getProcurementPlanDeadLine() );
        procurementPlan.procurementPlanQuantity( procurementPlanDTO.getProcurementPlanQuantity() );
        procurementPlan.procurementPlanStatus( procurementPlanDTO.getProcurementPlanStatus() );

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

    private ContractStatus procurementPlanMaterialContractContractStatus(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        Contract contract = material.getContract();
        if ( contract == null ) {
            return null;
        }
        ContractStatus contractStatus = contract.getContractStatus();
        if ( contractStatus == null ) {
            return null;
        }
        return contractStatus;
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

    private LocalDateTime procurementPlanProductionPlanProductionPlanDeadLine(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        ProductionPlan productionPlan = procurementPlan.getProductionPlan();
        if ( productionPlan == null ) {
            return null;
        }
        LocalDateTime productionPlanDeadLine = productionPlan.getProductionPlanDeadLine();
        if ( productionPlanDeadLine == null ) {
            return null;
        }
        return productionPlanDeadLine;
    }

    private int procurementPlanMaterialInventoryAvailableQuantity(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return 0;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return 0;
        }
        Inventory inventory = material.getInventory();
        if ( inventory == null ) {
            return 0;
        }
        int availableQuantity = inventory.getAvailableQuantity();
        return availableQuantity;
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

    protected ProductionPlan procurementPlanDTOToProductionPlan(ProcurementPlanDTO procurementPlanDTO) {
        if ( procurementPlanDTO == null ) {
            return null;
        }

        ProductionPlan.ProductionPlanBuilder productionPlan = ProductionPlan.builder();

        productionPlan.productionPlanCode( procurementPlanDTO.getProductionPlanCode() );

        return productionPlan.build();
    }
}
