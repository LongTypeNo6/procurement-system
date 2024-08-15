package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"procurementPlan","warehousing"})
@Getter
@Table(name="tbl_purchase_order")
public class PurchaseOrder {

    @Id
    private String purchaseOrderCode;
    private LocalDateTime purchaseOrderDate;
    private String purchaseOrderMemo;

    @OneToOne
    @JoinColumn(name = "procurement_plan_code")
    private ProcurementPlan procurementPlan;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PurchaseOrderStatus purchaseOrderStatus = PurchaseOrderStatus.PENDING;

    public void changePurchaseOrderStatus(PurchaseOrderStatus newStatus) { this.purchaseOrderStatus = newStatus; }

    public void changePurchaseOrderDate(LocalDateTime newPurchaseOrderDate) { this.purchaseOrderDate= newPurchaseOrderDate; }

    public void changePurchaseOrderMemo(String newPurchaseOrderMemo) { this.purchaseOrderMemo= newPurchaseOrderMemo; }

    @OneToOne(mappedBy = "purchaseOrder",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Warehousing warehousing;
}
