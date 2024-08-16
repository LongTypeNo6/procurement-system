package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.FileDTO;
import site.junggam.procurement_system.entity.FileEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-16T20:28:32+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileDTO toDTO(FileEntity fileEntity) {
        if ( fileEntity == null ) {
            return null;
        }

        FileDTO.FileDTOBuilder fileDTO = FileDTO.builder();

        fileDTO.fileCode( fileEntity.getFileCode() );
        fileDTO.originalName( fileEntity.getOriginalName() );
        fileDTO.storedName( fileEntity.getStoredName() );
        fileDTO.filePath( fileEntity.getFilePath() );
        fileDTO.fileType( fileEntity.getFileType() );
        fileDTO.fileSize( fileEntity.getFileSize() );

        return fileDTO.build();
    }

    @Override
    public FileEntity toEntity(FileDTO fileDTO) {
        if ( fileDTO == null ) {
            return null;
        }

        FileEntity.FileEntityBuilder fileEntity = FileEntity.builder();

        fileEntity.fileCode( fileDTO.getFileCode() );
        fileEntity.originalName( fileDTO.getOriginalName() );
        fileEntity.storedName( fileDTO.getStoredName() );
        fileEntity.filePath( fileDTO.getFilePath() );
        fileEntity.fileType( fileDTO.getFileType() );
        fileEntity.fileSize( fileDTO.getFileSize() );

        return fileEntity.build();
    }
}
