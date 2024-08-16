package site.junggam.procurement_system.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.TemMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-16T20:28:32+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class InspectionPlanMapperImpl implements InspectionPlanMapper {

    @Override
    public InspectionPlanDTO toDTO(InspectionPlan inspectionPlan) {
        if ( inspectionPlan == null ) {
            return null;
        }

        InspectionPlanDTO.InspectionPlanDTOBuilder inspectionPlanDTO = InspectionPlanDTO.builder();

        inspectionPlanDTO.purchaseOrderDTO( purchaseOrderToPurchaseOrderDTO( inspectionPlan.getPurchaseOrder() ) );
        inspectionPlanDTO.purchaserAddress( inspectionPlanPurchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserAddress( inspectionPlan ) );
        inspectionPlanDTO.inspectionPlanCode( inspectionPlan.getInspectionPlanCode() );
        inspectionPlanDTO.inspectionPlanDateTime( inspectionPlan.getInspectionPlanDateTime() );
        inspectionPlanDTO.inspectionPlanMemo( inspectionPlan.getInspectionPlanMemo() );
        inspectionPlanDTO.inspectionPlanProgress( inspectionPlan.getInspectionPlanProgress() );
        inspectionPlanDTO.inspectionPlanComplementary( inspectionPlan.getInspectionPlanComplementary() );
        inspectionPlanDTO.inspectionPlanDeliveryProgress( inspectionPlan.getInspectionPlanDeliveryProgress() );
        inspectionPlanDTO.inspectionPlanStatus( inspectionPlan.getInspectionPlanStatus() );
        inspectionPlanDTO.inspectionResultDateTime( inspectionPlan.getInspectionResultDateTime() );

        return inspectionPlanDTO.build();
    }

    @Override
    public List<InspectionPlanDTO> toDTOs(List<InspectionPlan> inspectionPlans) {
        if ( inspectionPlans == null ) {
            return null;
        }

        List<InspectionPlanDTO> list = new ArrayList<InspectionPlanDTO>( inspectionPlans.size() );
        for ( InspectionPlan inspectionPlan : inspectionPlans ) {
            list.add( toDTO( inspectionPlan ) );
        }

        return list;
    }

    @Override
    public InspectionPlan toEntity(InspectionPlanDTO inspectionPlanDTO) {
        if ( inspectionPlanDTO == null ) {
            return null;
        }

        InspectionPlan.InspectionPlanBuilder inspectionPlan = InspectionPlan.builder();

        inspectionPlan.purchaseOrder( purchaseOrderDTOToPurchaseOrder( inspectionPlanDTO.getPurchaseOrderDTO() ) );
        inspectionPlan.inspectionPlanCode( inspectionPlanDTO.getInspectionPlanCode() );
        inspectionPlan.inspectionPlanDateTime( inspectionPlanDTO.getInspectionPlanDateTime() );
        inspectionPlan.inspectionPlanMemo( inspectionPlanDTO.getInspectionPlanMemo() );
        inspectionPlan.inspectionPlanStatus( inspectionPlanDTO.getInspectionPlanStatus() );
        inspectionPlan.inspectionResultDateTime( inspectionPlanDTO.getInspectionResultDateTime() );
        inspectionPlan.inspectionPlanProgress( inspectionPlanDTO.getInspectionPlanProgress() );
        inspectionPlan.inspectionPlanDeliveryProgress( inspectionPlanDTO.getInspectionPlanDeliveryProgress() );
        inspectionPlan.inspectionPlanComplementary( inspectionPlanDTO.getInspectionPlanComplementary() );

        return inspectionPlan.build();
    }

    private LocalDateTime purchaseOrderProcurementPlanProcurementPlanDeadLine(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        LocalDateTime procurementPlanDeadLine = procurementPlan.getProcurementPlanDeadLine();
        if ( procurementPlanDeadLine == null ) {
            return null;
        }
        return procurementPlanDeadLine;
    }

    private Integer purchaseOrderProcurementPlanProcurementPlanQuantity(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        Integer procurementPlanQuantity = procurementPlan.getProcurementPlanQuantity();
        if ( procurementPlanQuantity == null ) {
            return null;
        }
        return procurementPlanQuantity;
    }

    private String purchaseOrderProcurementPlanTemMaterialMaterialName(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialName = temMaterial.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String purchaseOrderProcurementPlanTemMaterialMaterialStand(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialStand = temMaterial.getMaterialStand();
        if ( materialStand == null ) {
            return null;
        }
        return materialStand;
    }

    private String purchaseOrderProcurementPlanTemMaterialMaterialTexture(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialTexture = temMaterial.getMaterialTexture();
        if ( materialTexture == null ) {
            return null;
        }
        return materialTexture;
    }

    private Double purchaseOrderProcurementPlanTemMaterialContractContractPrice(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Double contractPrice = contract.getContractPrice();
        if ( contractPrice == null ) {
            return null;
        }
        return contractPrice;
    }

    private String purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserName(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserName = purchaser.getPurchaserName();
        if ( purchaserName == null ) {
            return null;
        }
        return purchaserName;
    }

    private String purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManager(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserManager = purchaser.getPurchaserManager();
        if ( purchaserManager == null ) {
            return null;
        }
        return purchaserManager;
    }

    private String purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerTel(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserManagerTel = purchaser.getPurchaserManagerTel();
        if ( purchaserManagerTel == null ) {
            return null;
        }
        return purchaserManagerTel;
    }

    private String purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerEmail(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserManagerEmail = purchaser.getPurchaserManagerEmail();
        if ( purchaserManagerEmail == null ) {
            return null;
        }
        return purchaserManagerEmail;
    }

    private String purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerFax(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserManagerFax = purchaser.getPurchaserManagerFax();
        if ( purchaserManagerFax == null ) {
            return null;
        }
        return purchaserManagerFax;
    }

    protected PurchaseOrderDTO purchaseOrderToPurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderDTO.PurchaseOrderDTOBuilder purchaseOrderDTO = PurchaseOrderDTO.builder();

        purchaseOrderDTO.procurementPlanDeadLine( purchaseOrderProcurementPlanProcurementPlanDeadLine( purchaseOrder ) );
        Integer procurementPlanQuantity = purchaseOrderProcurementPlanProcurementPlanQuantity( purchaseOrder );
        if ( procurementPlanQuantity != null ) {
            purchaseOrderDTO.procurementPlanQuantity( procurementPlanQuantity );
        }
        purchaseOrderDTO.materialName( purchaseOrderProcurementPlanTemMaterialMaterialName( purchaseOrder ) );
        purchaseOrderDTO.materialStand( purchaseOrderProcurementPlanTemMaterialMaterialStand( purchaseOrder ) );
        purchaseOrderDTO.materialTexture( purchaseOrderProcurementPlanTemMaterialMaterialTexture( purchaseOrder ) );
        Double contractPrice = purchaseOrderProcurementPlanTemMaterialContractContractPrice( purchaseOrder );
        if ( contractPrice != null ) {
            purchaseOrderDTO.contractPrice( contractPrice );
        }
        purchaseOrderDTO.purchaserName( purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserName( purchaseOrder ) );
        purchaseOrderDTO.purchaserManager( purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManager( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerTel( purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerTel( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerEmail( purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerEmail( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerFax( purchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserManagerFax( purchaseOrder ) );
        purchaseOrderDTO.purchaseOrderCode( purchaseOrder.getPurchaseOrderCode() );
        purchaseOrderDTO.purchaseOrderDate( purchaseOrder.getPurchaseOrderDate() );
        purchaseOrderDTO.purchaseOrderMemo( purchaseOrder.getPurchaseOrderMemo() );
        purchaseOrderDTO.purchaseOrderStatus( purchaseOrder.getPurchaseOrderStatus() );

        return purchaseOrderDTO.build();
    }

    private String inspectionPlanPurchaseOrderProcurementPlanTemMaterialContractPurchaserPurchaserAddress(InspectionPlan inspectionPlan) {
        if ( inspectionPlan == null ) {
            return null;
        }
        PurchaseOrder purchaseOrder = inspectionPlan.getPurchaseOrder();
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserAddress = purchaser.getPurchaserAddress();
        if ( purchaserAddress == null ) {
            return null;
        }
        return purchaserAddress;
    }

    protected PurchaseOrder purchaseOrderDTOToPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        if ( purchaseOrderDTO == null ) {
            return null;
        }

        PurchaseOrder.PurchaseOrderBuilder purchaseOrder = PurchaseOrder.builder();

        purchaseOrder.purchaseOrderCode( purchaseOrderDTO.getPurchaseOrderCode() );
        purchaseOrder.purchaseOrderDate( purchaseOrderDTO.getPurchaseOrderDate() );
        purchaseOrder.purchaseOrderMemo( purchaseOrderDTO.getPurchaseOrderMemo() );
        purchaseOrder.purchaseOrderStatus( purchaseOrderDTO.getPurchaseOrderStatus() );

        return purchaseOrder.build();
    }
}
