package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "temMaterial")
@Table(name="tbl_inventory")
public class Inventory {

    @Id
    private String materialCode;

    @MapsId
    @OneToOne
    @JoinColumn(name = "material_code")
    private TemMaterial temMaterial;

    private int materialQuantity; //실재고수량
    private int releaseDesireSumQuantity; //모든 출고요청희망 수량
    private int procurementPlanSumQuantity; //모든발주수량
    private int warehousingSumQuantity; //모든입고수량
    private double contractAvgPrice; //평균단가

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
