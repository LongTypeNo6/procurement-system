package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Release;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {
    ReleaseMapper INSTANCE = Mappers.getMapper(ReleaseMapper.class);

    @Mapping(source = "material.inventory.availableQuantity", target = "availableQuantity")
    @Mapping(source = "material.inventory.materialQuantity", target = "materialQuantity")
    @Mapping(source = "material.materialCode", target = "materialCode")
    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "material.materialStand", target = "materialStand")
    @Mapping(source = "material.materialTexture", target = "materialTexture")
    @Mapping(source = "material.contract.purchaser.purchaserCode", target = "purchaserCode")
    @Mapping(source = "material.contract.purchaser.purchaserName", target = "purchaserName")
    ReleaseDTO toDTO(Release release);

    @Mapping(source = "materialCode", target = "material.materialCode")
    Release toEntity(ReleaseDTO releaseDTO);

}
