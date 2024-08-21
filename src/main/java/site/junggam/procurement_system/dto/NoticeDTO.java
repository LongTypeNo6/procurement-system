package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.Notice;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NoticeDTO {


    private String noticeId;

    private String noticeTitle;

    private Integer noticeNumber;

    private String noticeContent;

    private LocalDateTime noticeRegDate;


    public Notice toEntity() {
        return Notice.builder()
                .noticeId(this.noticeId)
                .noticeTitle(this.noticeTitle)
                .noticeNumber(this.noticeNumber)
                .noticeContent(this.noticeContent)
                .noticeRegDate(this.noticeRegDate)
                .build();
    }




}
