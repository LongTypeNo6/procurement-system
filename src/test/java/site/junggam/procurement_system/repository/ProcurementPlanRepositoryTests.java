package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.TemMaterial;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ProcurementPlanRepositoryTests {
    @Autowired
    private ProcurementPlanRepository procurementPlanRepository;

    @Test
    public void insertProcurementPlan(){
        IntStream.rangeClosed(1,20).forEach(i->{
            ProcurementPlan procurementPlan = ProcurementPlan.builder()
                    .procurementPlanCode("PPC-"+i)
                    .procurementPlanDeadLine(LocalDateTime.now())
                    .procurementPlantRegDate(LocalDateTime.now())
                    .procurementPlanQuantity(i)
                    .temMaterial(TemMaterial.builder().materialCode("MATE-"+i).build())
                    .build();
            procurementPlanRepository.save(procurementPlan);
        });
    }

    @Test
    @Transactional
    public void getProcurementPlan(){
        String procurementPlanCode = "PPC-14";
        Optional<ProcurementPlan> procurementPlan=procurementPlanRepository.findById(procurementPlanCode);
        System.out.println(procurementPlan.get().getTemMaterial());
//        System.out.println(procurementPlan.get());
    }
}
