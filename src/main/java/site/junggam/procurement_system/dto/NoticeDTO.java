package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private String noticeTitle;

    private String noticeContent;

    private LocalDate noticeRegDate;

    private LocalDate noticeUpdateDate;

    private Integer noticeNumber;

    private String noticeWriter;

}
