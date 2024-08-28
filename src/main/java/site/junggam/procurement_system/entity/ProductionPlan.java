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

    @Builder.Default
    private LocalDateTime productionPlanRegDate=LocalDateTime.now();
    private LocalDateTime productionPlanDate;
    private LocalDateTime productionPlanDeadLine;
    private Integer productionPlanQuantity;

    @Builder.Default
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCode")
    private Product product=null;

    @Builder.Default
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unitCode")
    private Unit unit=null;


}
