package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.entity.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "temMaterial.materialCode", target = "materialCode")
    @Mapping(source = "temMaterial.materialName", target = "materialName")
    @Mapping(source = "temMaterial.materialSafeQuantity", target = "materialSafeQuantity")
    InventoryDTO toDTO(Inventory inventory);

    @Mapping(source = "materialCode", target = "temMaterial.materialCode")
    Inventory toEntity(InventoryDTO inventoryDTO);
}
