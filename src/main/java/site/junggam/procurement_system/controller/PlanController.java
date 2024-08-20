package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    //입고대기리스트
    @RequestMapping("/procurementPlanList")
    public void procurementPlanList() {
    }
}
