package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"purchaseOrder"})
@Getter
@Table(name="tbl_inspection_plan")
public class InspectionPlan {

    //검수계획에대한 속성
    @Id
    private String inspectionPlanCode;
    private LocalDateTime inspectionPlanDateTime;

    @Builder.Default
    private String inspectionPlanMemo=null;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InspectionPlanStatus inspectionPlanStatus = InspectionPlanStatus.NOT_INSPECTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseOrderCode")
    private PurchaseOrder purchaseOrder;

    //검수결과에대한 속성
    private LocalDateTime inspectionResultDateTime=null;

    @Builder.Default
    private Integer inspectionPlanProgress=0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InspectionPlanDeliveryProgress inspectionPlanDeliveryProgress=InspectionPlanDeliveryProgress.NOT_INSPECTED;

    @Builder.Default
    private String inspectionPlanComplementary=null;

    public void changeInspectionPlanStatus(InspectionPlanStatus newStatus) {this.inspectionPlanStatus=newStatus;}
    public void changeInspectionPlanDateTime(LocalDateTime newDateTime) {this.inspectionPlanDateTime=newDateTime;}
    public void changeInspectionPlanProgress(Integer newProgress) {this.inspectionPlanProgress=newProgress;}
    public void changeInspectionPlanDeliveryProgress(InspectionPlanDeliveryProgress newDeliveryProgress) {this.inspectionPlanDeliveryProgress=newDeliveryProgress;}
    public void changeInspectionPlanMemo(String newMemo) {this.inspectionPlanMemo=newMemo;}
    public void changeInspectionResultDateTime(LocalDateTime newDateTime) {this.inspectionResultDateTime=newDateTime;}
    public void changeInspectionPlanComplementary(String newComplementary) {this.inspectionPlanComplementary = newComplementary;}
}
