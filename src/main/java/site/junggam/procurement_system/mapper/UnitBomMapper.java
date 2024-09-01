package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.UnitBomDTO;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InventoryMapper.class})
public interface UnitBomMapper {

    UnitBomMapper INSTANCE = Mappers.getMapper(UnitBomMapper.class);

    @Mapping(source = "material.inventory", target = "inventoryDTO")
    @Mapping(source = "unit.unitName", target = "unitName")
    @Mapping(source = "unit.unitCode", target = "unitCode")
    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "material.materialCode", target = "materialCode")
    @Mapping(source = "material.materialStand", target = "materialStand")
    @Mapping(source = "material.materialTexture", target = "materialTexture")
    UnitBomDTO toDTO(UnitBom unitBom);

    List<UnitBomDTO> toDTOs(List<UnitBom> unitBoms);

    @Mapping(source = "unitName", target = "unit.unitName")
    @Mapping(source = "unitCode", target = "unit.unitCode")
    @Mapping(source = "materialName", target = "material.materialName")
    @Mapping(source = "materialCode", target = "material.materialCode")
    UnitBom toEntity(UnitBomDTO unitBomDTO);
}
