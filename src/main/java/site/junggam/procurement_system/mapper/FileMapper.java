package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.FileDTO;
import site.junggam.procurement_system.entity.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileDTO toDTO(FileEntity fileEntity);

    FileEntity toEntity(FileDTO fileDTO);
}