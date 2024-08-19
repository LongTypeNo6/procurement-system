package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="tbl_inventory_history")
public class InventoryHistory {
    @Id
    private String inventoryHistoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materialCode")
    private Inventory inventory;

    private LocalDateTime transactionDate;
    private int quantityChange; // 재고의 증감량
    private int finalQuantity;  // 이 트랜잭션 이후의 최종 재고 수량
    @Enumerated(EnumType.STRING)
    private InventoryHistoryStatus transactionType; // 트랜잭션 유형 (예: 입고, 출고)
    private String transactionReference; // 관련 트랜잭션 ID (예: WarehousingHistory 또는 Release의 ID)
}
