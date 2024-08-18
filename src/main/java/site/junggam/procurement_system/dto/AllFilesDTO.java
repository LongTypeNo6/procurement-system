package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllFilesDTO {

    private Long inum;

    private String fileName;

    private String path;

    private Long fileSize;

    private String fileType;

    private String foreignCode;
}
