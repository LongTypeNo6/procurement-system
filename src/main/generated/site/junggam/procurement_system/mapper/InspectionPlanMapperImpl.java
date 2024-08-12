package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-12T17:05:48+0900",
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
        inspectionPlanDTO.inspectionPlanCode( inspectionPlan.getInspectionPlanCode() );
        inspectionPlanDTO.inspectionPlanDateTime( inspectionPlan.getInspectionPlanDateTime() );
        inspectionPlanDTO.inspectionPlanMemo( inspectionPlan.getInspectionPlanMemo() );
        inspectionPlanDTO.inspectionPlanProgress( inspectionPlan.getInspectionPlanProgress() );
        inspectionPlanDTO.inspectionPlanComplementary( inspectionPlan.getInspectionPlanComplementary() );
        inspectionPlanDTO.inspectionPlanDeliveryProgress( inspectionPlan.getInspectionPlanDeliveryProgress() );
        inspectionPlanDTO.inspectionPlanStatus( inspectionPlan.getInspectionPlanStatus() );

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
        inspectionPlan.inspectionPlanProgress( inspectionPlanDTO.getInspectionPlanProgress() );
        inspectionPlan.inspectionPlanComplementary( inspectionPlanDTO.getInspectionPlanComplementary() );
        inspectionPlan.inspectionPlanDeliveryProgress( inspectionPlanDTO.getInspectionPlanDeliveryProgress() );
        inspectionPlan.inspectionPlanStatus( inspectionPlanDTO.getInspectionPlanStatus() );

        return inspectionPlan.build();
    }

    protected PurchaseOrderDTO purchaseOrderToPurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderDTO.PurchaseOrderDTOBuilder purchaseOrderDTO = PurchaseOrderDTO.builder();

        purchaseOrderDTO.purchaseOrderCode( purchaseOrder.getPurchaseOrderCode() );
        purchaseOrderDTO.purchaseOrderDate( purchaseOrder.getPurchaseOrderDate() );
        purchaseOrderDTO.purchaseOrderMemo( purchaseOrder.getPurchaseOrderMemo() );
        purchaseOrderDTO.purchaseOrderStatus( purchaseOrder.getPurchaseOrderStatus() );

        return purchaseOrderDTO.build();
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
