package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.WarehousingHistoryStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingHistoryDTO {
    private String warehousingCode;
    private String warehousingHistoryCode;
    private LocalDateTime warehousingDate;
    private String warehousingShipmentSpec ;
    private String warehousingSpec;
    private String warehousingResultMemo;
    private Integer warehousingQuantity;

    @Builder.Default
    private WarehousingHistoryStatus warehousingHistoryStatus=WarehousingHistoryStatus.PENDING;

}
