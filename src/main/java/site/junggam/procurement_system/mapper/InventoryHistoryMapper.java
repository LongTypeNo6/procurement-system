package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.InventoryHistoryDTO;
import site.junggam.procurement_system.entity.InventoryHistory;

@Mapper(componentModel = "spring")
public interface InventoryHistoryMapper {
    InventoryHistoryMapper INSTANCE = Mappers.getMapper(InventoryHistoryMapper.class);

    @Mapping(source = "inventory.materialCode", target = "materialCode")
    @Mapping(source = "inventory.material.materialName", target = "materialName")
    InventoryHistoryDTO toDto(InventoryHistory inventoryHistory);

    InventoryHistory toEntity(InventoryHistoryDTO inventoryHistoryDTO);

}
