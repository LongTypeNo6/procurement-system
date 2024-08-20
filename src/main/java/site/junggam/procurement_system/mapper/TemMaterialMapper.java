package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.entity.TemMaterial;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemMaterialMapper {
    TemMaterialMapper INSTANCE = Mappers.getMapper(TemMaterialMapper.class);


    TemMaterialDTO toDTO(TemMaterial temMaterial);

    List<TemMaterialDTO> toDTOs(List<TemMaterial> temMaterials);

    TemMaterial toEntity(TemMaterialDTO temMaterialDTO);

}
