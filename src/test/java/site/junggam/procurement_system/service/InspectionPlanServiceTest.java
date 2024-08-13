package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.InspectionPlan;

import java.util.List;

@SpringBootTest
public class InspectionPlanServiceTest {

    @Autowired
    private InspectionPlanService inspectionPlanService;

    @Test
    @Transactional
    public void testGetInspectionPlan() {
        System.out.println("여기다!!!!!!!!");
        String purchaseOrderCode="PO1";
        List<InspectionPlanDTO> inspectionPlan=inspectionPlanService.getInspectionPlan(purchaseOrderCode);
        inspectionPlan.forEach(f->{
            System.out.println("결과값 : "+f);
        });
    }

    @Test
    @Transactional
    public void testAllInspectionPlans() {
        PageResultDTO<InspectionPlanDTO,InspectionPlan> list = inspectionPlanService.getInspectionPlanList(PageRequestDTO.builder().page(1).size(10).build());
        System.out.println(list);

    }

    @Test
    @Transactional
    public void testModifyInspectionPlan() {


    }
}
