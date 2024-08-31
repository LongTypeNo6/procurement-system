package site.junggam.procurement_system.mapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Warehousing;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-29T15:26:00+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class WarehousingMapperImpl implements WarehousingMapper {

    @Override
    public WarehousingDTO toDTO(Warehousing warehousing) {
        if ( warehousing == null ) {
            return null;
        }

        WarehousingDTO.WarehousingDTOBuilder warehousingDTO = WarehousingDTO.builder();

        warehousingDTO.purchaseOrderDTO( purchaseOrderToPurchaseOrderDTO( warehousing.getPurchaseOrder() ) );
        warehousingDTO.warehousingCode( warehousing.getWarehousingCode() );
        warehousingDTO.warehousingStatus( warehousing.getWarehousingStatus() );

        return warehousingDTO.build();
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

    private String purchaseOrderProcurementPlanMaterialMaterialCode(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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

    private String purchaseOrderProcurementPlanMaterialMaterialName(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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

    private String purchaseOrderProcurementPlanMaterialMaterialStand(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialStand = material.getMaterialStand();
        if ( materialStand == null ) {
            return null;
        }
        return materialStand;
    }

    private String purchaseOrderProcurementPlanMaterialMaterialTexture(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        Material material = procurementPlan.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialTexture = material.getMaterialTexture();
        if ( materialTexture == null ) {
            return null;
        }
        return materialTexture;
    }

    private Double purchaseOrderProcurementPlanMaterialContractContractPrice(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserCode(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserName(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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
        String purchaserName = purchaser.getPurchaserName();
        if ( purchaserName == null ) {
            return null;
        }
        return purchaserName;
    }

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManager(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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
        String purchaserManager = purchaser.getPurchaserManager();
        if ( purchaserManager == null ) {
            return null;
        }
        return purchaserManager;
    }

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerTel(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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
        String purchaserManagerTel = purchaser.getPurchaserManagerTel();
        if ( purchaserManagerTel == null ) {
            return null;
        }
        return purchaserManagerTel;
    }

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerEmail(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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
        String purchaserManagerEmail = purchaser.getPurchaserManagerEmail();
        if ( purchaserManagerEmail == null ) {
            return null;
        }
        return purchaserManagerEmail;
    }

    private String purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerFax(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = purchaseOrder.getProcurementPlan();
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
        purchaseOrderDTO.materialCode( purchaseOrderProcurementPlanMaterialMaterialCode( purchaseOrder ) );
        purchaseOrderDTO.materialName( purchaseOrderProcurementPlanMaterialMaterialName( purchaseOrder ) );
        purchaseOrderDTO.materialStand( purchaseOrderProcurementPlanMaterialMaterialStand( purchaseOrder ) );
        purchaseOrderDTO.materialTexture( purchaseOrderProcurementPlanMaterialMaterialTexture( purchaseOrder ) );
        Double contractPrice = purchaseOrderProcurementPlanMaterialContractContractPrice( purchaseOrder );
        if ( contractPrice != null ) {
            purchaseOrderDTO.contractPrice( contractPrice );
        }
        purchaseOrderDTO.purchaserCode( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserCode( purchaseOrder ) );
        purchaseOrderDTO.purchaserName( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserName( purchaseOrder ) );
        purchaseOrderDTO.purchaserManager( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManager( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerTel( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerTel( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerEmail( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerEmail( purchaseOrder ) );
        purchaseOrderDTO.purchaserManagerFax( purchaseOrderProcurementPlanMaterialContractPurchaserPurchaserManagerFax( purchaseOrder ) );
        purchaseOrderDTO.purchaseOrderCode( purchaseOrder.getPurchaseOrderCode() );
        purchaseOrderDTO.purchaseOrderDate( purchaseOrder.getPurchaseOrderDate() );
        purchaseOrderDTO.purchaseOrderMemo( purchaseOrder.getPurchaseOrderMemo() );
        purchaseOrderDTO.purchaseOrderStatus( purchaseOrder.getPurchaseOrderStatus() );

        return purchaseOrderDTO.build();
    }
}
