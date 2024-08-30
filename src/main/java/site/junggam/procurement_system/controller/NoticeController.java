package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.entity.DeptNotice;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.service.DeptNoticeService;
import site.junggam.procurement_system.service.NoticeService;

import javax.inject.Qualifier;


@Controller
@Log4j2
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final DeptNoticeService deptNoticeService;

//    @GetMapping("/noticelist")
//    public String noticelist(Model model) {
//        log.info("공지사항 목록");
//        model.addAttribute("notice", noticeService.list());
//        // log.info("보낼데이터"+noticeService.list());
//        model.addAttribute("deptnotice", deptNoticeService.list());
//        // log.info("데이터" + deptNoticeService.list());
//        return "notice/noticelist";
//    }

    @GetMapping("/noticelist")
    public String noticelist(Model model, @PageableDefault(page = 0, size = 5, sort = "noticeNumber",
    direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("공지사항 목록");
        Page<Notice> list = noticeService.list(pageable);
        // Page<DeptNotice> list1 = deptNoticeService.list(pageable1);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage+5, list.getTotalPages());

//        int nowPage1 = list1.getPageable().getPageNumber() + 1;
//        int startPage1 = Math.max(nowPage1-4, 1);
//        int endPage1 = Math.min(nowPage1+5, list1.getTotalPages());

       //  model.addAttribute("list", noticeService.list(pageable));
        model.addAttribute("notice", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        // log.info("보낼데이터"+noticeService.list());

    model.addAttribute("deptnotice", deptNoticeService.list());
//        model.addAttribute("deptnotice", list1);
//        model.addAttribute("nowPage1", nowPage1);
//        model.addAttribute("startPage1", startPage1);
//        model.addAttribute("endPage1", endPage1);
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

}
