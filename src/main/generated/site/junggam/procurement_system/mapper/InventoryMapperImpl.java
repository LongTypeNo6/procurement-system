package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Purchaser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-12T23:24:44+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryDTO toDTO(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryDTO.InventoryDTOBuilder inventoryDTO = InventoryDTO.builder();

        inventoryDTO.purchaserCode( inventoryMaterialContractPurchaserPurchaserCode( inventory ) );
        inventoryDTO.purchaserName( inventoryMaterialContractPurchaserPurchaserName( inventory ) );
        inventoryDTO.materialCode( inventoryMaterialMaterialCode( inventory ) );
        inventoryDTO.materialName( inventoryMaterialMaterialName( inventory ) );
        Integer materialSafeQuantity = inventoryMaterialMaterialSafeQuantity( inventory );
        if ( materialSafeQuantity != null ) {
            inventoryDTO.materialSafeQuantity( materialSafeQuantity );
        }
        inventoryDTO.availableQuantity( inventory.getAvailableQuantity() );
        inventoryDTO.totalPrice( inventory.getTotalPrice() );
        inventoryDTO.materialQuantity( inventory.getMaterialQuantity() );
        inventoryDTO.releaseDesireSumQuantity( inventory.getReleaseDesireSumQuantity() );
        inventoryDTO.warehousingPendingQuantity( inventory.getWarehousingPendingQuantity() );
        inventoryDTO.contractAvgPrice( inventory.getContractAvgPrice() );

        return inventoryDTO.build();
    }

    @Override
    public Inventory toEntity(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        Inventory.InventoryBuilder inventory = Inventory.builder();

        inventory.material( inventoryDTOToMaterial( inventoryDTO ) );
        inventory.materialCode( inventoryDTO.getMaterialCode() );
        inventory.materialQuantity( inventoryDTO.getMaterialQuantity() );
        inventory.releaseDesireSumQuantity( inventoryDTO.getReleaseDesireSumQuantity() );
        inventory.warehousingPendingQuantity( inventoryDTO.getWarehousingPendingQuantity() );
        inventory.contractAvgPrice( inventoryDTO.getContractAvgPrice() );

        return inventory.build();
    }

    private String inventoryMaterialContractPurchaserPurchaserCode(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        Material material = inventory.getMaterial();
        if ( material == null ) {
            return null;
        }
        Contract contract = material.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserCode = purchaser.getPurchaserCode();
        if ( purchaserCode == null ) {
            return null;
        }
        return purchaserCode;
    }

    private String inventoryMaterialContractPurchaserPurchaserName(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        Material material = inventory.getMaterial();
        if ( material == null ) {
            return null;
        }
        Contract contract = material.getContract();
        if ( contract == null ) {
            return null;
        }
        Purchaser purchaser = contract.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserName = purchaser.getPurchaserName();
        if ( purchaserName == null ) {
            return null;
        }
        return purchaserName;
    }

    private String inventoryMaterialMaterialCode(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        Material material = inventory.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialCode = material.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String inventoryMaterialMaterialName(Inventory inventory) {
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

    private Integer inventoryMaterialMaterialSafeQuantity(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        Material material = inventory.getMaterial();
        if ( material == null ) {
            return null;
        }
        Integer materialSafeQuantity = material.getMaterialSafeQuantity();
        if ( materialSafeQuantity == null ) {
            return null;
        }
        return materialSafeQuantity;
    }

    protected Material inventoryDTOToMaterial(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        Material.MaterialBuilder material = Material.builder();

        material.materialCode( inventoryDTO.getMaterialCode() );

        return material.build();
    }
}
