package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.repository.InspectionPlanRepository;

import java.util.List;

@SpringBootTest
public class InspectionPlanServiceTest {

    @Autowired
    private InspectionPlanService inspectionPlanService;
    @Autowired
    private InspectionPlanRepository inspectionPlanRepository;

    @Test
    @Transactional
    public void testGetInspectionPlan() {
        System.out.println("여기다!!!!!!!!");
        String purchaseOrderCode="PURC-240813-001-001";
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
    private int preInspectionPlanProgress(String inspectionPlanCode){
        String codeExcludingNum=inspectionPlanCode.substring(0,inspectionPlanCode.length()-1);
        int preNum=Integer.parseInt(inspectionPlanCode.substring(inspectionPlanCode.length()-1))-1;
        if(preNum==0){
            return 0;
        }else {
            String preInspectionPlanCode=codeExcludingNum+preNum;
            return inspectionPlanRepository.findById(preInspectionPlanCode).get().getInspectionPlanProgress();
        }
    }
    @Test
    public void testpreInspectionPlanProgress() {
        String code="INSP-240813-001-018-2";
        int result =preInspectionPlanProgress(code);
        System.out.println("결과값은"+result);

    }
}
