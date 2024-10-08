package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.ContractStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private String contractCode;
    private String contractFile;
    private String contractMemo;
    private Double contractPrice;
    private Integer contractLeadTime;

    @Builder.Default
    private LocalDateTime contractDate = LocalDateTime.now();


    // DTO 클래스에 대한 필드
    private MaterialDTO materialDTO;
    private PurchaserDTO purchaserDTO;



}
