package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.junggam.procurement_system.entity.DeptNotice;
import site.junggam.procurement_system.service.DeptNoticeService;

@Controller
@Log4j2
@RequestMapping("/deptnotice")
@RequiredArgsConstructor
public class DeptNoticeController {

    private final DeptNoticeService deptNoticeService;

    @GetMapping("/deptnoticeread")
    public String deptnoticeread(@RequestParam("deptNoticeNumber") Integer deptNoticeNumber, Model model) {
        log.info("부서 알림 상세보기");
        model.addAttribute("deptnotice", deptNoticeService.read(deptNoticeNumber));
        return "deptnotice/deptnoticeread";
    }

    @GetMapping("/deptnoticeregister")
    public String deptnoticeregister() {
        log.info("부서 알림 등록");
        return "deptnotice/deptnoticeregister";
    }

    @PostMapping("/deptnoticeregister")
    public String deptregisterpost(DeptNotice deptNotice) {
        log.info("부서 알림 등록 post");
        deptNoticeService.register(deptNotice);
        return "redirect:/notice/noticelist";
    }

    @GetMapping("/deptnoticemodify")
    public String deptnoticemodify(@RequestParam("deptNoticeNumber") Integer deptNoticeNumber, Model model) {
        log.info("부서 알림 수정");
        model.addAttribute("deptnotice", deptNoticeService.read(deptNoticeNumber));
        return "deptnotice/deptnoticemodify";
    }

    @PostMapping("/deptnoticemodify")
    public String deptmodifypost(@RequestParam("deptNoticeNumber") Integer deptNoticeNumber, DeptNotice deptNotice) {
        log.info("부서 알림 수정 post");
        DeptNotice deptNoticeTemp = deptNoticeService.read(deptNoticeNumber);
        deptNoticeTemp.setDeptNoticeNumber(deptNotice.getDeptNoticeNumber());
        deptNoticeTemp.setDeptNoticeWriter(deptNotice.getDeptNoticeWriter());
        deptNoticeTemp.setDeptNoticeTitle(deptNotice.getDeptNoticeTitle());
        deptNoticeTemp.setDeptNoticeRegDate(deptNotice.getDeptNoticeRegDate());
        deptNoticeTemp.setDeptNoticeContent(deptNotice.getDeptNoticeContent());
        deptNoticeTemp.setDeptNoticeUpdateDate(deptNotice.getDeptNoticeUpdateDate());
        deptNoticeService.register(deptNoticeTemp);
        return "redirect:/notice/noticelist";
    }

    @GetMapping("/deptnoticedelete")
    public String deptnoticedelete(@RequestParam("deptNoticeNumber") Integer deptNoticeNumber) {
        log.info("부서 알림 삭제");
        deptNoticeService.delete(deptNoticeNumber);
        return "redirect:/notice/noticelist";
    }


}
