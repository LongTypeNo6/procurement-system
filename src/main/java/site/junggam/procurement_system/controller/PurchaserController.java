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
@RequestMapping("/purchaser")
@RequiredArgsConstructor
public class PurchaserController {

    @RequestMapping("/purchaserList")
    public void purchaserList() {}

    @GetMapping("/purchaserGet")
    public void getPurchaser(@RequestParam("purchaserCode") String purchaserCode, Model model){
        log.info("발주서 상세보기 컨트롤러");
        log.info("발주코드는 "+purchaserCode);
        model.addAttribute("purchaserCode",purchaserCode);
    }
}
