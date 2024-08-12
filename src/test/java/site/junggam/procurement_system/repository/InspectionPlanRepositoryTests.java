package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class InspectionPlanRepositoryTests {

    @Autowired
    private InspectionPlanRepository inspectionPlanRepository;

    @Test
    public void isertInspectionPlan() {
        IntStream.rangeClosed(1,20).forEach(i -> {
            InspectionPlan inspectionPlan = InspectionPlan.builder()
                    .inspectionPlanCode("IPC-"+i)
                    .inspectionPlanDateTime(LocalDateTime.now())
                    .purchaseOrder(PurchaseOrder.builder().purchaseOrderCode("PO"+i).build())
                    .build();
            inspectionPlanRepository.save(inspectionPlan);
        });
    }

    @Test
    public void getInspectionPlan() {
        String inspectionPlanCode = "IPC-1";
        System.out.println("아래에 표시됨");
        Optional<InspectionPlan> inspectionPlan = inspectionPlanRepository.findById(inspectionPlanCode);
        if (inspectionPlan.isPresent()) {
            System.out.println(inspectionPlan.get());
        }
    }

}
