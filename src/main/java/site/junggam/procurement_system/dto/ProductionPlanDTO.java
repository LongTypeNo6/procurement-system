package site.junggam.procurement_system.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionPlanDTO {

    private String productionPlanCode;
    private LocalDateTime productionPlanRegDate;
    private LocalDateTime productionPlanDate;
    private LocalDateTime productionPlanDeadLine;
    private Integer productionPlanQuantity;

    @Builder.Default
    private String productCode=null;
    @Builder.Default
    private String productName=null;

    @Builder.Default
    private String unitCode=null;
    @Builder.Default
    private String unitName=null;
}
