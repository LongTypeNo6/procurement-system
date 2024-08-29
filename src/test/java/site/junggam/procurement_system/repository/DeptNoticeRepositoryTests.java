package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.DeptNotice;

import java.time.LocalDate;

@SpringBootTest
public class DeptNoticeRepositoryTests {

    @Autowired
    private DeptNoticeRepository deptNoticeRepository;

    @Test
    public void insertDeptNotice() {
        DeptNotice deptNotice = DeptNotice.builder().deptNoticeTitle("intp : ... ...")
                .deptNoticeContent("intp : ... ...")
                .deptNoticeRegDate(LocalDate.now()).deptNoticeWriter("자재부서 최길동").build();
        deptNoticeRepository.save(deptNotice);

    }

}
