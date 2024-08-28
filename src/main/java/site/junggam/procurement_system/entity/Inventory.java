package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "material")
@Table(name="tbl_inventory")
public class Inventory {

    @Id
    private String materialCode;

    @MapsId
    @OneToOne
    @JoinColumn(name = "material_code")
    private Material material;

    @Builder.Default
    private int materialQuantity=0; //실재고수량
    @Builder.Default
    private int releaseDesireSumQuantity=0; //모든 출고요청희망 수량
    @Builder.Default
    private int procurementPlanSumQuantity=0; //모든발주수량
    @Builder.Default
    private int warehousingSumQuantity=0; //모든입고수량
    @Builder.Default
    private double contractAvgPrice=0; //평균단가

    // 계산 필드는 getter 메서드에서 계산하도록 변경
    public int getAvailableQuantity() {
        return materialQuantity - releaseDesireSumQuantity;
    }

    public int getWarehousingPendingQuantity() {
        return procurementPlanSumQuantity - warehousingSumQuantity;
    }

    public double getTotalPrice() {
        return materialQuantity * contractAvgPrice;
    }
}
