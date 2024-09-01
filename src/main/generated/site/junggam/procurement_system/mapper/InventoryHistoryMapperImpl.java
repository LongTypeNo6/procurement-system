package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.InventoryHistoryDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.InventoryHistory;
import site.junggam.procurement_system.entity.Material;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-01T17:07:40+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class InventoryHistoryMapperImpl implements InventoryHistoryMapper {

    @Override
    public InventoryHistoryDTO toDto(InventoryHistory inventoryHistory) {
        if ( inventoryHistory == null ) {
            return null;
        }

        InventoryHistoryDTO.InventoryHistoryDTOBuilder inventoryHistoryDTO = InventoryHistoryDTO.builder();

        inventoryHistoryDTO.materialCode( inventoryHistoryInventoryMaterialCode( inventoryHistory ) );
        inventoryHistoryDTO.materialName( inventoryHistoryInventoryMaterialMaterialName( inventoryHistory ) );
        inventoryHistoryDTO.inventoryHistoryCode( inventoryHistory.getInventoryHistoryCode() );
        inventoryHistoryDTO.transactionDate( inventoryHistory.getTransactionDate() );
        inventoryHistoryDTO.quantityChange( inventoryHistory.getQuantityChange() );
        inventoryHistoryDTO.finalQuantity( inventoryHistory.getFinalQuantity() );
        inventoryHistoryDTO.transactionType( inventoryHistory.getTransactionType() );
        inventoryHistoryDTO.transactionReference( inventoryHistory.getTransactionReference() );
        inventoryHistoryDTO.transactionCounterparty( inventoryHistory.getTransactionCounterparty() );

        return inventoryHistoryDTO.build();
    }

    @Override
    public InventoryHistory toEntity(InventoryHistoryDTO inventoryHistoryDTO) {
        if ( inventoryHistoryDTO == null ) {
            return null;
        }

        InventoryHistory.InventoryHistoryBuilder inventoryHistory = InventoryHistory.builder();

        inventoryHistory.inventoryHistoryCode( inventoryHistoryDTO.getInventoryHistoryCode() );
        inventoryHistory.transactionDate( inventoryHistoryDTO.getTransactionDate() );
        inventoryHistory.quantityChange( inventoryHistoryDTO.getQuantityChange() );
        inventoryHistory.finalQuantity( inventoryHistoryDTO.getFinalQuantity() );
        inventoryHistory.transactionType( inventoryHistoryDTO.getTransactionType() );
        inventoryHistory.transactionReference( inventoryHistoryDTO.getTransactionReference() );
        inventoryHistory.transactionCounterparty( inventoryHistoryDTO.getTransactionCounterparty() );

        return inventoryHistory.build();
    }

    private String inventoryHistoryInventoryMaterialCode(InventoryHistory inventoryHistory) {
        if ( inventoryHistory == null ) {
            return null;
        }
        Inventory inventory = inventoryHistory.getInventory();
        if ( inventory == null ) {
            return null;
        }
        String materialCode = inventory.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String inventoryHistoryInventoryMaterialMaterialName(InventoryHistory inventoryHistory) {
        if ( inventoryHistory == null ) {
            return null;
        }
        Inventory inventory = inventoryHistory.getInventory();
        if ( inventory == null ) {
            return null;
        }
        Material material = inventory.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialName = material.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }
}
