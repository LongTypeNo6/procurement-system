package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"purchaseOrder"})
@Getter
@Table(name="tbl_warehousing")
public class Warehousing {
    @Id
    private String warehousingCode;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WarehousingStatus warehousingStatus=WarehousingStatus.PENDING;

    @OneToOne
    @JoinColumn(name = "purchaseOrderCode")
    private PurchaseOrder purchaseOrder;

}
