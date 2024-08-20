package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.ProductBomDTO;
import site.junggam.procurement_system.dto.ProductionPlanDTO;
import site.junggam.procurement_system.dto.UnitBomDTO;
import site.junggam.procurement_system.entity.ProductionPlan;
import site.junggam.procurement_system.repository.ProductionPlanRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @Autowired
    private ProductionPlanRepository productionPlanRepository;

    @Test
    @Transactional
    public void findUnitBom() {
        List<UnitBomDTO> list = planService.getUnitBomList("BU-00005");
        list.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void findProductBom2() {
        List<ProductBomDTO> list = planService.getProductBomList("BP-00007");
        list.forEach(System.out::println);
    }

    private String generatePlanCode(String productionPlanDate) {
        // 1. 그 날짜의 마지막 코드 가져오기
        String dateCode="PLAN-"+productionPlanDate;
        String lastPlanCode=productionPlanRepository.findLastIdOfDate(dateCode);
        // 2. 만약 마지막 코드가 null이거나 다른 날짜라면 001로 시작
        String newSequence = "001";
        if (lastPlanCode != null) {
            // 3. 마지막 코드의 일련번호 추출
            String lastSequence = lastPlanCode.substring(12);

            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;

            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%03d", nextSequence);
        }
        // 6. 최종 코드를 생성
        return "PLAN-" + productionPlanDate + "-" + newSequence;
    }

    @Test
    @Transactional
    public void testNewPPC() {
        System.out.println("다음 넣을 코드는 "+generatePlanCode("240821"));
    }

    @Test
    public void testNewProductionPlanVerP() {
        ProductionPlanDTO productionPlanDTO = ProductionPlanDTO.builder()
                .productionPlanQuantity(40)
                .productionPlanRegDate(LocalDateTime.now())
                .productionPlanDate(LocalDateTime.now().plusDays(3L))
                .productionPlanDeadLine(LocalDateTime.now().plusDays(5L))
                .productCode("BP-00008")
                .build();
        planService.insertProductionPlan(productionPlanDTO);
    }

    @Test
    public void testNewProductionPlanVerU() {
        ProductionPlanDTO productionPlanDTO = ProductionPlanDTO.builder()
                .productionPlanQuantity(5)
                .productionPlanRegDate(LocalDateTime.now())
                .productionPlanDate(LocalDateTime.now().plusDays(3L))
                .productionPlanDeadLine(LocalDateTime.now().plusDays(5L))
                .unitCode("BU-00008")
                .build();
        planService.insertProductionPlan(productionPlanDTO);
    }
}
