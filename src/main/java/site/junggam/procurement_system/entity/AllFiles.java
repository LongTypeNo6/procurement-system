package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name="tbl_all_files")
public class AllFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String fileName;

    private String path;

    private Long fileSize;

    private String fileType;

    private String foreignCode;
}
