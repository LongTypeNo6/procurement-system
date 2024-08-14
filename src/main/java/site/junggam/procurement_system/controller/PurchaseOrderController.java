package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.service.InspectionPlanService;
import site.junggam.procurement_system.service.PurchaseOrderService;

@Log4j2
@Controller
@RequestMapping("/purchaseorder")
@RequiredArgsConstructor
public class PurchaseOrderController {
    private final InspectionPlanService inspectionPlanService;

    @RequestMapping("/purchaseOrderList")
    public void purchaseOrderList(){
        log.info("발주리스트 페이지 진입 완료");

    }
    @GetMapping("/purchaseOrderGet")
    public void getPurchaseOrder(@RequestParam("purchaseOrderCode") String purchaseOrderCode, Model model){
        log.info("발주서 상세보기 컨트롤러");
        log.info("발주코드는 "+purchaseOrderCode);
        model.addAttribute("purchaseOrderCode",purchaseOrderCode);
    }

    @RequestMapping("/inspectionPlanList")
    public void inspectionPlanList(){
        log.info("검수관리리스트 페이지 진입 완료");
    }

    @GetMapping("/inspectionPlanGet")
    public void inspectionPlanGet(@RequestParam("purchaseOrderCode") String purchaseOrderCode, Model model){
        log.info("검수관리 상세보기 컨트롤러");
        model.addAttribute("purchaseOrderCode",purchaseOrderCode);
        model.addAttribute("inspectionPlanCount",inspectionPlanService.getInspectionPlanCount(PurchaseOrder.builder().purchaseOrderCode(purchaseOrderCode).build()));
    }

    @RequestMapping("/inspectionPlanScheduleList")
    public void inspectionPlanScheduleList(){
        log.info("검수관리리스트 페이지 진입 완료");
    }
}
