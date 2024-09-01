package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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



@Controller
@Log4j2
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final DeptNoticeService deptNoticeService;

    @GetMapping("/noticelist")
    public String noticelist(Model model,
                             @PageableDefault(page = 0, size = 5, sort = "noticeNumber", direction = Sort.Direction.DESC) Pageable noticepageable,
                             @RequestParam(value = "deptPage", defaultValue = "0") int deptPage,
                             @RequestParam(value = "deptSize", defaultValue = "5") int deptSize) {

        log.info("공지사항 목록");

        // 공지사항 페이지 처리
        Page<Notice> noticeList = noticeService.list(noticepageable);
        int noticeNowPage = noticeList.getPageable().getPageNumber() + 1;
        int noticeStartPage = Math.max(noticeNowPage-4, 1);
        int noticeEndPage = Math.min(noticeNowPage+5, noticeList.getTotalPages());

        // model.addAttribute("list", noticeService.list(pageable));
        model.addAttribute("notice", noticeList);
        model.addAttribute("noticeNowPage", noticeNowPage);
        model.addAttribute("noticeStartPage", noticeStartPage);
        model.addAttribute("noticeEndPage", noticeEndPage);

        // 부서 알림 공지사항 페이지 처리
        Pageable deptPageable = PageRequest.of(deptPage, deptSize, Sort.by("deptNoticeNumber").descending());
        Page<DeptNotice> deptNoticeList = deptNoticeService.list(deptPageable);
        int deptNowPage = deptNoticeList.getPageable().getPageNumber() + 1;
        int deptStartPage = Math.max(deptNowPage - 4, 1);
        int deptEndPage = Math.min(deptNowPage + 5, deptNoticeList.getTotalPages());

        // model.addAttribute("deptnotice", deptNoticeService.list(noticepageable));
        model.addAttribute("deptnotice", deptNoticeList);
        model.addAttribute("deptNowPage", deptNowPage);
        model.addAttribute("deptStartPage", deptStartPage);
        model.addAttribute("deptEndPage", deptEndPage);

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
