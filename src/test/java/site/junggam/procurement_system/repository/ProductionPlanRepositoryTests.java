package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ProductionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductionPlanRepositoryTests {
    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @Test
    public void insertProductionPlan() {
        IntStream.rangeClosed(1,9).forEach(i->{
            ProductionPlan productionPlan = ProductionPlan.builder()
                    .productionPlanCode("PLAN-240821-00"+i)
                    .productionPlanDate(LocalDateTime.now())
                    .productionPlanDeadLine(LocalDateTime.now())
                    .productionPlanRegDate(LocalDateTime.now())
                    .productionPlanQuantity(i*10)
                    .build();
            productionPlanRepository.save(productionPlan);
        });
        IntStream.rangeClosed(10,15).forEach(i->{
            ProductionPlan productionPlan = ProductionPlan.builder()
                    .productionPlanCode("PLAN-240821-0"+i)
                    .productionPlanDate(LocalDateTime.now())
                    .productionPlanDeadLine(LocalDateTime.now())
                    .productionPlanRegDate(LocalDateTime.now())
                    .productionPlanQuantity(i*10)
                    .build();
            productionPlanRepository.save(productionPlan);
        });

    }
}
