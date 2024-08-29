package site.junggam.procurement_system.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ProcurementPlanDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Estimate;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.MaterialContractStatus;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ProductionPlan;
import site.junggam.procurement_system.entity.Purchaser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-29T09:22:49+0900",
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

        procurementPlanDTO.purchaserCode( procurementPlanMaterialContractPurchaserPurchaserCode( procurementPlan ) );
        Double contractPrice = procurementPlanMaterialContractContractPrice( procurementPlan );
        if ( contractPrice != null ) {
            procurementPlanDTO.contractPrice( contractPrice );
        }
        Integer contractLeadTime = procurementPlanMaterialContractContractLeadTime( procurementPlan );
        if ( contractLeadTime != null ) {
            procurementPlanDTO.contractLeadTime( contractLeadTime );
        }
        procurementPlanDTO.contractMemo( procurementPlanMaterialContractContractMemo( procurementPlan ) );
        procurementPlanDTO.estimateMemo( procurementPlanMaterialEstimateEstimateMemo( procurementPlan ) );
        procurementPlanDTO.contractCode( procurementPlanMaterialContractContractCode( procurementPlan ) );
        procurementPlanDTO.estimateCode( procurementPlanMaterialEstimateEstimateCode( procurementPlan ) );
        procurementPlanDTO.materialContractStatus( procurementPlanMaterialMaterialContractStatus( procurementPlan ) );
        procurementPlanDTO.materialName( procurementPlanMaterialMaterialName( procurementPlan ) );
        procurementPlanDTO.materialCode( procurementPlanMaterialMaterialCode( procurementPlan ) );
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

    private String procurementPlanMaterialContractPurchaserPurchaserCode(ProcurementPlan procurementPlan) {
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
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserCode = purchaser.getPurchaserCode();
        if ( purchaserCode == null ) {
            return null;
        }
        return purchaserCode;
    }

    private Double procurementPlanMaterialContractContractPrice(ProcurementPlan procurementPlan) {
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
        Double contractPrice = contract.getContractPrice();
        if ( contractPrice == null ) {
            return null;
        }
        return contractPrice;
    }

    private Integer procurementPlanMaterialContractContractLeadTime(ProcurementPlan procurementPlan) {
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
        Integer contractLeadTime = contract.getContractLeadTime();
        if ( contractLeadTime == null ) {
            return null;
        }
        return contractLeadTime;
    }

    private String procurementPlanMaterialContractContractMemo(ProcurementPlan procurementPlan) {
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
        String contractMemo = contract.getContractMemo();
        if ( contractMemo == null ) {
            return null;
        }
        return contractMemo;
    }

    private String procurementPlanMaterialEstimateEstimateMemo(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        Estimate estimate = material.getEstimate();
        if ( estimate == null ) {
            return null;
        }
        String estimateMemo = estimate.getEstimateMemo();
        if ( estimateMemo == null ) {
            return null;
        }
        return estimateMemo;
    }

    private String procurementPlanMaterialContractContractCode(ProcurementPlan procurementPlan) {
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
        String contractCode = contract.getContractCode();
        if ( contractCode == null ) {
            return null;
        }
        return contractCode;
    }

    private String procurementPlanMaterialEstimateEstimateCode(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        Estimate estimate = material.getEstimate();
        if ( estimate == null ) {
            return null;
        }
        String estimateCode = estimate.getEstimateCode();
        if ( estimateCode == null ) {
            return null;
        }
        return estimateCode;
    }

    private MaterialContractStatus procurementPlanMaterialMaterialContractStatus(ProcurementPlan procurementPlan) {
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        MaterialContractStatus materialContractStatus = material.getMaterialContractStatus();
        if ( materialContractStatus == null ) {
            return null;
        }
        return materialContractStatus;
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
