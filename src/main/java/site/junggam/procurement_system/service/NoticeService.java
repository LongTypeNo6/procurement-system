package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.repository.NoticeRepository;



@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public void register(Notice notice) {
        noticeRepository.save(notice);
    }

    public Page<Notice> list(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    public Notice read(Integer noticeNumber) {
        return noticeRepository.findById(noticeNumber).get();
    }

    public void modify(Notice notice) {
        noticeRepository.save(notice);
    }

    public void delete(Integer noticeNumber) {
        noticeRepository.deleteById(noticeNumber);
    }

}
