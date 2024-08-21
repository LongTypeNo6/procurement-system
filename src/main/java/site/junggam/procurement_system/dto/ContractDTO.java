package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.ContractStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private String contractCode;
    private String contractFile;
    private Double contractPrice;
    private Integer contractLeadTime;

    // DTO 클래스에 대한 필드
    private MaterialDTO materialDTO;
    private PurchaserDTO purchaserDTO;

    @Builder.Default
    private ContractStatus contractStatus = ContractStatus.PENDING;
}
