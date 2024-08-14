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
@Table(name="tbl_warehousing")
public class Warehousing {
    @Id
    private String warehousingCode;
    private LocalDateTime warehousingDate;
    private String warehousingShipmentSpec ;
    private String warehousingSpec;
    private String warehousingResultMemo;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WarehousingStatus warehousingStatus=WarehousingStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseOrderCode")
    private PurchaseOrder purchaseOrder;

}
