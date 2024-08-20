package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Release;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {
    ReleaseMapper INSTANCE = Mappers.getMapper(ReleaseMapper.class);

    @Mapping(source = "temMaterial.inventory.availableQuantity", target = "availableQuantity")
    @Mapping(source = "temMaterial.inventory.materialQuantity", target = "materialQuantity")
    @Mapping(source = "temMaterial.materialCode", target = "materialCode")
    @Mapping(source = "temMaterial.materialName", target = "materialName")
    @Mapping(source = "temMaterial.materialStand", target = "materialStand")
    @Mapping(source = "temMaterial.materialTexture", target = "materialTexture")
    @Mapping(source = "temMaterial.contract.purchaser.purchaserCode", target = "purchaserCode")
    @Mapping(source = "temMaterial.contract.purchaser.purchaserName", target = "purchaserName")
    ReleaseDTO toDTO(Release release);

    @Mapping(source = "materialCode", target = "temMaterial.materialCode")
    Release toEntity(ReleaseDTO releaseDTO);

}
