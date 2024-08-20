package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.TemMaterial;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private String materialCode;
    private String materialName;
    private int materialSafeQuantity;
    private int materialQuantity; //실재고수량
    private int releaseDesireSumQuantity; //모든 출고요청희망 수량
    private int procurementPlanSumQuantity; //모든발주수량
    private int warehousingSumQuantity; //모든입고수량
    private double contractAvgPrice; //평균단가

    // 계산 필드는 getter 메서드에서 계산하도록 변경
    //가용재고
    public int getAvailableQuantity() {
        return materialQuantity - releaseDesireSumQuantity;
    }

    //입고대기수량
    public int getWarehousingPendingQuantity() {
        return procurementPlanSumQuantity - warehousingSumQuantity;
    }

    //각 자재별 총액
    public double getTotalPrice() {
        return materialQuantity * contractAvgPrice;
    }
}
