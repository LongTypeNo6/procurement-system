package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Material;
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
        IntStream.rangeClosed(1,9).forEach(i->{
            ProcurementPlan procurementPlan = ProcurementPlan.builder()
                    .procurementPlanCode("PROC-240813-001-00"+i)
                    .procurementPlanDeadLine(LocalDateTime.now())
                    .procurementPlanRegDate(LocalDateTime.now())
                    .procurementPlanQuantity(i)
                    .material(Material.builder().materialCode("BM-0000"+i).build())
                    .build();
            procurementPlanRepository.save(procurementPlan);
        });
        IntStream.rangeClosed(10,20).forEach(i->{
            ProcurementPlan procurementPlan = ProcurementPlan.builder()
                    .procurementPlanCode("PROC-240813-001-0"+i)
                    .procurementPlanDeadLine(LocalDateTime.now())
                    .procurementPlanRegDate(LocalDateTime.now())
                    .procurementPlanQuantity(i)
                    .material(Material.builder().materialCode("BM-000"+i).build())
                    .build();
            procurementPlanRepository.save(procurementPlan);
        });
    }

    @Test
    @Transactional
    public void getProcurementPlan(){
        String procurementPlanCode = "PPC-14";
        Optional<ProcurementPlan> procurementPlan=procurementPlanRepository.findById(procurementPlanCode);
        System.out.println(procurementPlan.get().getMaterial());
//        System.out.println(procurementPlan.get());
    }
}
