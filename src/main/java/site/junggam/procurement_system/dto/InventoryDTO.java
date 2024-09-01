package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int warehousingPendingQuantity; //입고 대기수량
    private double contractAvgPrice; //평균단가

    public int availableQuantity;
    public double totalPrice;
    private String purchaserCode;
    private String purchaserName;
}
