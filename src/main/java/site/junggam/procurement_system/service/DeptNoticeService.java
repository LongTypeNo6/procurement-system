package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.entity.DeptNotice;
import site.junggam.procurement_system.repository.DeptNoticeRepository;

@Service
@RequiredArgsConstructor
public class DeptNoticeService {

    private final DeptNoticeRepository deptNoticeRepository;

    public void register(DeptNotice deptNotice) {
        deptNoticeRepository.save(deptNotice);
    }

    public Page<DeptNotice> list(Pageable pageable) {
        return deptNoticeRepository.findAll(pageable);
    }

    public DeptNotice read(Integer deptNoticeNumber) {
        return deptNoticeRepository.findById(deptNoticeNumber).get();
    }

    public void modify(DeptNotice deptNotice) {
        deptNoticeRepository.save(deptNotice);
    }

    public void delete(Integer deptNoticeNumber) {
        deptNoticeRepository.deleteById(deptNoticeNumber);
    }

}
