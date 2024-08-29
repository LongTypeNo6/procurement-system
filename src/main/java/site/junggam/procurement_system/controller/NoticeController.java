package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.service.DeptNoticeService;
import site.junggam.procurement_system.service.NoticeService;



@Controller
@Log4j2
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final DeptNoticeService deptNoticeService;

    @GetMapping("/noticelist")
    public String noticelist(Model model) {
        log.info("공지사항 목록");
        model.addAttribute("notice", noticeService.list());
        // log.info("보낼데이터"+noticeService.list());
        model.addAttribute("deptnotice", deptNoticeService.list());
        // log.info("데이터" + deptNoticeService.list());
        return "notice/noticelist";
    }

    @GetMapping("/noticeread")
    public String noticeread(@RequestParam("noticeNumber") Integer noticeNumber, Model model) {
        log.info("공지사항 상세보기");
        model.addAttribute("notice", noticeService.read(noticeNumber));
        return "notice/noticeread";
    }

    @GetMapping("/noticeregister")
    public String noticeregister() {
        log.info("공지사항 등록");
        return "notice/noticeregister";
    }

    @PostMapping("/noticeregister")
    public String registerpost(Notice notice) {
        log.info("공지사항 등록 post");
        noticeService.register(notice);
        return "redirect:/notice/noticelist";
    }

    @GetMapping("/noticemodify")
    public String noticemodify(@RequestParam("noticeNumber") Integer noticeNumber, Model model) {
        log.info("공지사항 수정");
        model.addAttribute("notice", noticeService.read(noticeNumber));
        return "notice/noticemodify";
    }

    @PostMapping("/noticemodify")
    public String modifypost(@RequestParam("noticeNumber") Integer noticeNumber, Notice notice) {
        log.info("공지사항 수정 post");
        Notice noticeTemp = noticeService.read(noticeNumber);
        noticeTemp.setNoticeNumber(notice.getNoticeNumber());
        noticeTemp.setNoticeWriter(notice.getNoticeWriter());
        noticeTemp.setNoticeTitle(notice.getNoticeTitle());
        noticeTemp.setNoticeRegDate(notice.getNoticeRegDate());
        noticeTemp.setNoticeContent(notice.getNoticeContent());
        noticeTemp.setNoticeUpdateDate(notice.getNoticeUpdateDate());
        noticeService.register(noticeTemp);
        return "redirect:/notice/noticelist";
    }

    @GetMapping("/noticedelete")
    public String noticedelete(@RequestParam("noticeNumber") Integer noticeNumber) {
        log.info("공지사항 삭제");
        noticeService.delete(noticeNumber);
        return "redirect:/notice/noticelist";
    }

//    @GetMapping
//    public String list(Model model,
//                       @RequestParam(defaultValue = "0") int page,
//                       @RequestParam(defaultValue = "5") int size) {
//        Page<Notice> noticePage = noticeService.findAll(page, size);
//        model.addAttribute("noticePage", noticePage);
//        return "notice/list";
//    }

}
