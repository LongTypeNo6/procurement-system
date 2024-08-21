package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.NoticeDTO;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.repository.NoticeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2

public class NoticeService {
    private final NoticeRepository noticeRepository;

    // 등록
    @Transactional
    public void savePost(NoticeDTO noticeDTO) {
        Notice notice = noticeDTO.toEntity();
        noticeRepository.save(notice) ;
    }

    // 조회
    @Transactional
    public List<NoticeDTO> getNoticelist() {
        List<Notice> noticeEntities = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        for (Notice notice : noticeEntities) {
            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .noticeId(notice.getNoticeId())
                    .noticeTitle(notice.getNoticeTitle())
                    .noticeNumber(notice.getNoticeNumber())
                    .noticeContent(notice.getNoticeContent())
                    .noticeRegDate(notice.getNoticeRegDate())
                    .build();
            noticeDTOList.add(noticeDTO);
        }

        return noticeDTOList;
    }

    // 상세 조회
    @Transactional
    public NoticeDTO getPost(String noticeId) {
        Optional<Notice> noticeEntityWrapper = noticeRepository.findById(noticeId);
        Notice notice = noticeEntityWrapper.orElseThrow(() -> new RuntimeException("Notice not found"));

        return NoticeDTO.builder()
                .noticeId(notice.getNoticeId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeNumber(notice.getNoticeNumber())
                .noticeContent(notice.getNoticeContent())
                .noticeRegDate(notice.getNoticeRegDate())
                .build();
    }

    // 삭제
    @Transactional
    public void deletePost(String noticeId) {
        noticeRepository.deleteById(noticeId);
    }


}
