package site.junggam.procurement_system.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    // 회원 목록
    @GetMapping("/memberList")
    public void memberList() {
    }

    // 회원 상세(사용)
    @GetMapping("/memberDetail")
    public void memberDetail() {
    }

}
