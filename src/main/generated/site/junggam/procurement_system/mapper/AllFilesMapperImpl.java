package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.entity.AllFiles;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-20T16:06:15+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AllFilesMapperImpl implements AllFilesMapper {

    @Override
    public AllFilesDTO toDto(AllFiles allFiles) {
        if ( allFiles == null ) {
            return null;
        }

        AllFilesDTO.AllFilesDTOBuilder allFilesDTO = AllFilesDTO.builder();

        allFilesDTO.inum( allFiles.getInum() );
        allFilesDTO.fileName( allFiles.getFileName() );
        allFilesDTO.path( allFiles.getPath() );
        allFilesDTO.fileSize( allFiles.getFileSize() );
        allFilesDTO.fileType( allFiles.getFileType() );
        allFilesDTO.foreignCode( allFiles.getForeignCode() );

        return allFilesDTO.build();
    }

    @Override
    public AllFiles toEntity(AllFilesDTO allFilesDTO) {
        if ( allFilesDTO == null ) {
            return null;
        }

        AllFiles.AllFilesBuilder allFiles = AllFiles.builder();

        allFiles.inum( allFilesDTO.getInum() );
        allFiles.fileName( allFilesDTO.getFileName() );
        allFiles.path( allFilesDTO.getPath() );
        allFiles.fileSize( allFilesDTO.getFileSize() );
        allFiles.fileType( allFilesDTO.getFileType() );
        allFiles.foreignCode( allFilesDTO.getForeignCode() );

        return allFiles.build();
    }
}
