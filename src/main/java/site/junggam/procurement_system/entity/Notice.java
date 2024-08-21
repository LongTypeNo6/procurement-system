package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_notice_board")
public class Notice {

    @Id
    private String noticeId;

    private String noticeTitle;

    private Integer noticeNumber;
    private String noticeContent;
    private LocalDateTime noticeRegDate;

    @Builder
    public Notice(String noticeId, String noticeTitle, Integer noticeNumber, String noticeContent, LocalDateTime noticeRegDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeNumber = noticeNumber;
        this.noticeContent = noticeContent;
        this.noticeRegDate = noticeRegDate;
    }
}
