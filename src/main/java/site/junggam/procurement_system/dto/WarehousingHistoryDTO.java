package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.WarehousingHistoryStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingHistoryDTO {
    private String warehousingCode;
    private String warehousingHistoryCode;
    private LocalDateTime warehousingDate;

    private Long warehousingShipmentSpec ;
    private Long warehousingSpec ;

    private String warehousingResultMemo;
    private Integer warehousingQuantity;

    @Builder.Default
    private WarehousingHistoryStatus warehousingHistoryStatus = WarehousingHistoryStatus.PENDING;
}

