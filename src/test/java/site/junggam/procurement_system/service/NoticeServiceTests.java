package site.junggam.procurement_system.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.NoticeDTO;


import java.time.LocalDateTime;
import java.util.stream.IntStream;



@SpringBootTest
public class NoticeServiceTests {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testInsertNotices() {
        IntStream.rangeClosed(1, 4).forEach(i -> {
            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .noticeId("작성자"+i)
                    .noticeTitle("제목" + i)
                    .noticeNumber(i)
                    .noticeContent("공지사항 내용"+i)
                    .noticeRegDate(LocalDateTime.now())
                    .build();
            noticeService.savePost(noticeDTO);
        });


    }
}

