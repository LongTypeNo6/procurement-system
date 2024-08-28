package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    //생산계획리스트
    @RequestMapping("/productionPlanList")
    public void productionPlanList() {
    }

    //생산계획등록
    @GetMapping("/productionPlanResister")
    public void productionPlanGet(){
    }

    //생산계획상세보기
    @GetMapping("/productionPlanGet")
    public void productionPlanGet(@RequestParam("productionPlanCode") String productionPlanCode, Model model){
        log.info("발주서 상세보기 컨트롤러");
        log.info("발주코드는 "+productionPlanCode);
        model.addAttribute("productionPlanCode",productionPlanCode);
    }

    //조달계획리스트
    @RequestMapping("/procurementPlanList")
    public String procurementPlanList() {
        return "/plan/procurementPlanList";
    }

    //조달계획상세보기
    @GetMapping("/procurementPlanGet")
    public void procurementPlanGet(@RequestParam("procurementPlanCode") String procurementPlanCode, Model model){
        log.info("발주서 상세보기 컨트롤러");
        log.info("발주코드는 "+procurementPlanCode);
        model.addAttribute("procurementPlanCode",procurementPlanCode);
    }
}
