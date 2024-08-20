package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.TemMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-20T15:22:39+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public InventoryDTO toDTO(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryDTO.InventoryDTOBuilder inventoryDTO = InventoryDTO.builder();

        inventoryDTO.materialCode( inventoryTemMaterialMaterialCode( inventory ) );
        inventoryDTO.materialName( inventoryTemMaterialMaterialName( inventory ) );
        Integer materialSafeQuantity = inventoryTemMaterialMaterialSafeQuantity( inventory );
        if ( materialSafeQuantity != null ) {
            inventoryDTO.materialSafeQuantity( materialSafeQuantity );
        }
        inventoryDTO.materialQuantity( inventory.getMaterialQuantity() );
        inventoryDTO.releaseDesireSumQuantity( inventory.getReleaseDesireSumQuantity() );
        inventoryDTO.procurementPlanSumQuantity( inventory.getProcurementPlanSumQuantity() );
        inventoryDTO.warehousingSumQuantity( inventory.getWarehousingSumQuantity() );
        inventoryDTO.contractAvgPrice( inventory.getContractAvgPrice() );

        return inventoryDTO.build();
    }

    @Override
    public Inventory toEntity(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        Inventory.InventoryBuilder inventory = Inventory.builder();

        inventory.temMaterial( inventoryDTOToTemMaterial( inventoryDTO ) );
        inventory.materialCode( inventoryDTO.getMaterialCode() );
        inventory.materialQuantity( inventoryDTO.getMaterialQuantity() );
        inventory.releaseDesireSumQuantity( inventoryDTO.getReleaseDesireSumQuantity() );
        inventory.procurementPlanSumQuantity( inventoryDTO.getProcurementPlanSumQuantity() );
        inventory.warehousingSumQuantity( inventoryDTO.getWarehousingSumQuantity() );
        inventory.contractAvgPrice( inventoryDTO.getContractAvgPrice() );

        return inventory.build();
    }

    private String inventoryTemMaterialMaterialCode(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        TemMaterial temMaterial = inventory.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialCode = temMaterial.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String inventoryTemMaterialMaterialName(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        TemMaterial temMaterial = inventory.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialName = temMaterial.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private Integer inventoryTemMaterialMaterialSafeQuantity(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        TemMaterial temMaterial = inventory.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Integer materialSafeQuantity = temMaterial.getMaterialSafeQuantity();
        if ( materialSafeQuantity == null ) {
            return null;
        }
        return materialSafeQuantity;
    }

    protected TemMaterial inventoryDTOToTemMaterial(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        TemMaterial.TemMaterialBuilder temMaterial = TemMaterial.builder();

        temMaterial.materialCode( inventoryDTO.getMaterialCode() );

        return temMaterial.build();
    }
}
