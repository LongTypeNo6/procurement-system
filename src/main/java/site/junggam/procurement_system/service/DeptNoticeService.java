package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.DeptNoticeDTO;
import site.junggam.procurement_system.entity.DeptNotice;
import site.junggam.procurement_system.entity.Notice;
import site.junggam.procurement_system.repository.DeptNoticeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptNoticeService {

    private final DeptNoticeRepository deptNoticeRepository;

    public void register(DeptNotice deptNotice) {
        deptNoticeRepository.save(deptNotice);
    }

    public List<DeptNotice> list() {
        return deptNoticeRepository.findAll(Sort.by(Sort.Direction.DESC, "deptNoticeNumber"));
    }

//    public Page<DeptNotice> list(Pageable pageable) {
//        return deptNoticeRepository.findAll(pageable);
//    }

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
