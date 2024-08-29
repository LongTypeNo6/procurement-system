package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.repository.NoticeRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public void register(Notice notice) {
        noticeRepository.save(notice);
    }

    public List<Notice> list() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeNumber"));
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

//    public Page<Notice> findAll(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return noticeRepository.findAll(pageable);
//    }

}
