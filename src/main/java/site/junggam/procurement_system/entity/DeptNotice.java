package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_dept_notice_board")
@Builder
@AllArgsConstructor
@Setter
@ToString
@NoArgsConstructor
@Getter
public class DeptNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptNoticeNumber;

    private String deptNoticeTitle;

    private String deptNoticeContent;

    private String deptNoticeWriter;

    private LocalDate deptNoticeRegDate;

    private LocalDate deptNoticeUpdateDate;

}
