package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_files")
public class FileEntity {

    @Id
    private String fileCode;

    private String originalName; // 원본 파일명
    private String storedName; // 저장된 파일명
    private String filePath; // 파일 경로
    private String fileType; // 파일 타입 (MIME 타입)
    private long fileSize; // 파일 크기
    // 기타 필요한 속성들 (예: 업로드한 사용자 정보, 업로드 날짜 등)
}
