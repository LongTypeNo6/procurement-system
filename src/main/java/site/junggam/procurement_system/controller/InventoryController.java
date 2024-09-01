package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    //입고처리
    @RequestMapping("/warehousingGet")
    public void warehousingGet(@RequestParam("warehousingCode") String warehousingCode, Model model) {
        model.addAttribute("warehousingCode", warehousingCode);
    }

    //입고대기리스트
    @RequestMapping("/warehousingList")
    public void warehousingList() {
    }

    //출고요청리스트
    @RequestMapping("/releaseRequestList")
    public void releaseRequestList() {
    }

    //출고상세보기
    @RequestMapping("/releaseGet")
    public void releasetGet(@RequestParam("releaseCode") String releaseCode, Model model) {
        model.addAttribute("releaseCode", releaseCode);
    }

    //출고상세보기
    @RequestMapping("/releaseRequestGet")
    public void releaseRequestGet() {
    }

    //창고재고리스트
    @RequestMapping("/inventoryList")
    public void inventoryList() {
    }

    //입출고내역
    @RequestMapping("/inventoryHistoryList")
    public void inventoryHistoryList() {
    }

    //재고금액현황
    @RequestMapping("/inventoryPriceReport")
    public void inventoryPriceReport() {
    }

    //제품생산계산기
    @RequestMapping("/productionCalculatingMachine")
    public void productionCalculatingMachine() {
    }
}
