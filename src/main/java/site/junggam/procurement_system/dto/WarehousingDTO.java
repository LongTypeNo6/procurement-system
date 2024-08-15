package site.junggam.procurement_system.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.WarehousingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingDTO {

    private String warehousingCode;

    @Builder.Default
    private WarehousingStatus warehousingStatus=WarehousingStatus.PENDING;

    private PurchaseOrderDTO purchaseOrderDTO;

    @Builder.Default
    private List<WarehousingHistoryDTO> warehousingHistoryDTOS = new ArrayList<>();
}
