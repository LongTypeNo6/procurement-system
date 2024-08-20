package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.InspectionPlanDeliveryProgress;
import site.junggam.procurement_system.entity.InspectionPlanStatus;

import java.time.LocalDateTime;

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

    @Builder.Default
    private LocalDateTime inspectionResultDateTime=null;

    private String purchaserAddress;

    @Builder.Default
    private Integer previousInspectionPlanProgress = 0;

}
