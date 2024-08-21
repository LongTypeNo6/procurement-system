package site.junggam.procurement_system.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.NoticeDTO;
import site.junggam.procurement_system.service.NoticeService;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/notice")
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 등록
    // 등록(1) - 화면 보여주기
    @GetMapping("/noticepost")
    public String write() {
        return "notice/noticeread.html";
    }

    // 등록(2)
    @PostMapping("/noticepost")
    public String write(NoticeDTO noticeDTO) {
        noticeService.savePost(noticeDTO);
        return "redirect:/";
    }

    // 게시글 조회
    @GetMapping("/noticelist")
    public String noticelist(Model model) {
        log.info("공지사항 리스트 페이지 진입 완료");
        List<NoticeDTO> noticeList = noticeService.getNoticelist();
        model.addAttribute("list", noticeList);
        return "notice/noticelist.html";
    }

    // 상세 조회
    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") String id, Model model) {
        NoticeDTO noticeDTO = noticeService.getPost(id);
        model.addAttribute("noticeDTO", noticeDTO);
        return "notice/detail.html";
    }

    // 수정
    // 수정-1 작성
    @GetMapping("/noticemodify")
    public String edit(@PathVariable("id") String id, Model model) {
        NoticeDTO noticeDTO = noticeService.getPost(id);
        model.addAttribute("noticeDTO", noticeDTO);
        return "notice/update.html";
    }

    // 수정-2 등록
    @PostMapping("/noticemodify/edit/{id}")
    public String update(@PathVariable("id") String id, NoticeDTO noticeDTO) {
        noticeDTO.setNoticeId(noticeDTO.getNoticeId()); // ID를 DTO에 설정
        noticeService.savePost(noticeDTO);
        return "redirect:/";
    }

    // 삭제
    @PostMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        noticeService.deletePost(id);
        return "redirect:/";
    }
}
