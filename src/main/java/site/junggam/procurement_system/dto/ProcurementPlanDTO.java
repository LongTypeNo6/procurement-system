package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.ContractStatus;
import site.junggam.procurement_system.entity.ProcurementPlanStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcurementPlanDTO {
    private String procurementPlanCode;

    @Builder.Default
    private LocalDateTime procurementPlantRegDate = LocalDateTime.now();

    private LocalDateTime procurementPlanDeadLine;
    private Integer procurementPlanQuantity;

    private String materialCode;
    private String materialName;

    private String productionPlanCode;

    @Builder.Default
    private String bomProcess=null;
    @Builder.Default
    private int bomQuantity=0;

    @Builder.Default
    private ProcurementPlanStatus procurementPlanStatus=ProcurementPlanStatus.ESTABLISHED;

    private ContractStatus contractStatus;

}
