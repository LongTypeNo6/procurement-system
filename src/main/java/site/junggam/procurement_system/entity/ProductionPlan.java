package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "Product")
@Table(name="tbl_production_plan")
public class ProductionPlan {

    @Id
    private String productionPlanCode;
    private LocalDateTime productionPlanRegDate;
    private LocalDateTime productionPlanDate;
    private LocalDateTime productionPlanDeadLine;
    private Integer productionPlanQuantity;

    @Builder.Default
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCode")
    private Product Product=null;

    @Builder.Default
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnitCode")
    private Unit Unit=null;


}
