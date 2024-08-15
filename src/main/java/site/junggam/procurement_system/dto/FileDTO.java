package site.junggam.procurement_system.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {

    private Long id;

    private String originalName;

    private String storedName;

    private String filePath;

    private String fileType;

    private long fileSize;
}
