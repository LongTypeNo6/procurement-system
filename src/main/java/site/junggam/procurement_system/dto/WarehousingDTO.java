package site.junggam.procurement_system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.WarehousingStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingDTO {

    private String warehousingCode;
    private LocalDateTime warehousingDate;
    private String warehousingShipmentSpec ;
    private String warehousingSpec;
    private String warehousingResultMemo;

    @Builder.Default
    private WarehousingStatus warehousingStatus=WarehousingStatus.PENDING;

    private PurchaseOrderDTO purchaseOrderDTO;

}
