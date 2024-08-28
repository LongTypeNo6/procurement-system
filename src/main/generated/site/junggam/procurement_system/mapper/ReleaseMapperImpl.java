package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Release;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T14:55:51+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ReleaseMapperImpl implements ReleaseMapper {

    @Override
    public ReleaseDTO toDTO(Release release) {
        if ( release == null ) {
            return null;
        }

        ReleaseDTO.ReleaseDTOBuilder releaseDTO = ReleaseDTO.builder();

        releaseDTO.availableQuantity( releaseMaterialInventoryAvailableQuantity( release ) );
        releaseDTO.materialQuantity( releaseMaterialInventoryMaterialQuantity( release ) );
        releaseDTO.materialCode( releaseMaterialMaterialCode( release ) );
        releaseDTO.materialName( releaseMaterialMaterialName( release ) );
        releaseDTO.materialStand( releaseMaterialMaterialStand( release ) );
        releaseDTO.materialTexture( releaseMaterialMaterialTexture( release ) );
        releaseDTO.purchaserCode( releaseMaterialContractPurchaserPurchaserCode( release ) );
        releaseDTO.purchaserName( releaseMaterialContractPurchaserPurchaserName( release ) );
        releaseDTO.releaseCode( release.getReleaseCode() );
        releaseDTO.releaseRequestDept( release.getReleaseRequestDept() );
        releaseDTO.releaseRequestDate( release.getReleaseRequestDate() );
        releaseDTO.releaseDesireDate( release.getReleaseDesireDate() );
        if ( release.getReleaseDesireQuantity() != null ) {
            releaseDTO.releaseDesireQuantity( release.getReleaseDesireQuantity() );
        }
        releaseDTO.releaseRequestMemo( release.getReleaseRequestMemo() );
        releaseDTO.releaseDate( release.getReleaseDate() );
        if ( release.getReleaseQuantity() != null ) {
            releaseDTO.releaseQuantity( release.getReleaseQuantity() );
        }
        releaseDTO.releaseMemo( release.getReleaseMemo() );
        releaseDTO.releaseStaus( release.getReleaseStaus() );

        return releaseDTO.build();
    }

    @Override
    public Release toEntity(ReleaseDTO releaseDTO) {
        if ( releaseDTO == null ) {
            return null;
        }

        Release.ReleaseBuilder release = Release.builder();

        release.material( releaseDTOToMaterial( releaseDTO ) );
        release.releaseCode( releaseDTO.getReleaseCode() );
        release.releaseRequestDept( releaseDTO.getReleaseRequestDept() );
        release.releaseRequestDate( releaseDTO.getReleaseRequestDate() );
        release.releaseDesireDate( releaseDTO.getReleaseDesireDate() );
        release.releaseDesireQuantity( releaseDTO.getReleaseDesireQuantity() );
        release.releaseRequestMemo( releaseDTO.getReleaseRequestMemo() );
        release.releaseDate( releaseDTO.getReleaseDate() );
        release.releaseQuantity( releaseDTO.getReleaseQuantity() );
        release.releaseMemo( releaseDTO.getReleaseMemo() );
        release.releaseStaus( releaseDTO.getReleaseStaus() );

        return release.build();
    }

    private int releaseMaterialInventoryAvailableQuantity(Release release) {
        if ( release == null ) {
            return 0;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return 0;
        }
        Inventory inventory = material.getInventory();
        if ( inventory == null ) {
            return 0;
        }
        int availableQuantity = inventory.getAvailableQuantity();
        return availableQuantity;
    }

    private int releaseMaterialInventoryMaterialQuantity(Release release) {
        if ( release == null ) {
            return 0;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return 0;
        }
        Inventory inventory = material.getInventory();
        if ( inventory == null ) {
            return 0;
        }
        int materialQuantity = inventory.getMaterialQuantity();
        return materialQuantity;
    }

    private String releaseMaterialMaterialCode(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialCode = material.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String releaseMaterialMaterialName(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialName = material.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String releaseMaterialMaterialStand(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialStand = material.getMaterialStand();
        if ( materialStand == null ) {
            return null;
        }
        return materialStand;
    }

    private String releaseMaterialMaterialTexture(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialTexture = material.getMaterialTexture();
        if ( materialTexture == null ) {
            return null;
        }
        return materialTexture;
    }

    private String releaseMaterialContractPurchaserPurchaserCode(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
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

    private String releaseMaterialContractPurchaserPurchaserName(Release release) {
        if ( release == null ) {
            return null;
        }
        Material material = release.getMaterial();
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

    protected Material releaseDTOToMaterial(ReleaseDTO releaseDTO) {
        if ( releaseDTO == null ) {
            return null;
        }

        Material.MaterialBuilder material = Material.builder();

        material.materialCode( releaseDTO.getMaterialCode() );

        return material.build();
    }
}
