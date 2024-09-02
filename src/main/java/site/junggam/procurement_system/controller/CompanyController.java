package site.junggam.procurement_system.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/company")
public class CompanyController {

    // 부서 목록
    @GetMapping("/deptList")
    public void deptList() {
    }

    // 회사정보(자사정보)
    @GetMapping("/companyInfo")
    public void companyInfo() {
    }

}
