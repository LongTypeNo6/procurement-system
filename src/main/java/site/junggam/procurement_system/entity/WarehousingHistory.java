package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"warehousing"})
@Getter
@Table(name="tbl_warehousing_history")
public class WarehousingHistory {

    @Id
    private String warehousingHistoryCode;
    private LocalDateTime warehousingDate;
    private String warehousingShipmentSpec;
    private String warehousingSpec;
    private String warehousingResultMemo;
    private Integer warehousingQuantity;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WarehousingHistoryStatus warehousingHistoryStatus=WarehousingHistoryStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehousingCode")
    private Warehousing warehousing;

}
