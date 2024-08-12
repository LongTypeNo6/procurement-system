package site.junggam.procurement_system.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.InspectionPlanDeliveryProgress;
import site.junggam.procurement_system.entity.InspectionPlanStatus;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionPlanDTO {
    private String inspectionPlanCode;
    private LocalDateTime inspectionPlanDateTime;

    @Builder.Default
    private String inspectionPlanMemo=null;
    @Builder.Default
    private Integer inspectionPlanProgress=0;
    @Builder.Default
    private String inspectionPlanComplementary=null;

    @Builder.Default
    private InspectionPlanDeliveryProgress inspectionPlanDeliveryProgress=InspectionPlanDeliveryProgress.NOT_INSPECTED;

    @Builder.Default
    private InspectionPlanStatus inspectionPlanStatus = InspectionPlanStatus.NOT_INSPECTED;

    private PurchaseOrderDTO purchaseOrderDTO;

    private int inspectionPlanCount; //검수계획 개수


}
