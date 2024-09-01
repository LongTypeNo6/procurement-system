package site.junggam.procurement_system.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.InventoryHistoryStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryHistoryDTO {

    private long inventoryHistoryCode;

    private LocalDateTime transactionDate;
    private int quantityChange; // 재고의 증감량
    private int finalQuantity;  // 이 트랜잭션 이후의 최종 재고 수량
    @Enumerated(EnumType.STRING)
    private InventoryHistoryStatus transactionType; // 트랜잭션 유형 (예: 입고, 출고)
    private String transactionReference; // 관련 트랜잭션 ID (예: WarehousingHistory 또는 Release의 ID)
    private String transactionCounterparty; //관련 트랜잭션의 요청처 혹은 거래처

    private String materialCode;
    private String materialName;
}
