package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private String contractCode;
    private String contractFile ;
    private Double contractPrice;
    private Integer contractLeadTime;
    private MaterialDTO MaterialDTO;
    private PurchaserDTO purchaserDTO;
}
