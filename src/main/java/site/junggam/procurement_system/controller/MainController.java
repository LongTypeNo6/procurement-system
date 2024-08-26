package site.junggam.procurement_system.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/")
public class MainController {
    
    @RequestMapping("sample/logintest")
    public void logintest(){
        log.info("테스트하면서 로그포제이도 테스트");
        System.out.println("테스트하면서 시스아웃도 테스트");
    }

    @RequestMapping("layout/basic")
    public void basic(){
    }

    @RequestMapping("/main")
    public String main(){
        return "main/main";
    }

}
