package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.entity.Material;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    MaterialDTO toDTO(Material material);

    List<MaterialDTO> toDTOs(List<Material> materials);

    Material toEntity(MaterialDTO materialDTO);

}
