package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.entity.AllFiles;

@Mapper(componentModel = "spring")
public interface AllFilesMapper {
    AllFilesMapper INSTANCE= Mappers.getMapper(AllFilesMapper.class);

    AllFilesDTO toDto(AllFiles allFiles);

    AllFiles toEntity(AllFilesDTO allFilesDTO);
}
