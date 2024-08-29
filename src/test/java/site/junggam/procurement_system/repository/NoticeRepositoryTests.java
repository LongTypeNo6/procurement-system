package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Notice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class NoticeRepositoryTests {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void insertNotice() {
        Notice notice = Notice.builder().noticeContent("서비스 안정성 강화 및 성능 개선을 위한 시스템 업데이트가 완료되었습니다.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "감사합니다.")
                .noticeRegDate(LocalDate.now()).noticeTitle("시스템 업데이트 완료 안내")
                .noticeWriter("관리자").build();
        noticeRepository.save(notice);
    }
}
