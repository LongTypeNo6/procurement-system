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

    @RequestMapping("/warehousingGet")
    public void warehousingGet(@RequestParam("warehousingCode") String warehousingCode, Model model) {
        model.addAttribute("warehousingCode", warehousingCode);
    }

    @RequestMapping("/warehousingList")
    public void warehousingList() {
    }

    @RequestMapping("/fileload")
    public void fileload() {}
}
