package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Release;
import site.junggam.procurement_system.entity.TemMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-20T09:15:54+0900",
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

        releaseDTO.materialCode( releaseTemMaterialMaterialCode( release ) );
        releaseDTO.materialName( releaseTemMaterialMaterialName( release ) );
        releaseDTO.materialStand( releaseTemMaterialMaterialStand( release ) );
        releaseDTO.materialTexture( releaseTemMaterialMaterialTexture( release ) );
        releaseDTO.purchaserCode( releaseTemMaterialContractPurchaserPurchaserCode( release ) );
        releaseDTO.purchaserName( releaseTemMaterialContractPurchaserPurchaserName( release ) );
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

    private String releaseTemMaterialMaterialCode(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialCode = temMaterial.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String releaseTemMaterialMaterialName(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialName = temMaterial.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String releaseTemMaterialMaterialStand(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialStand = temMaterial.getMaterialStand();
        if ( materialStand == null ) {
            return null;
        }
        return materialStand;
    }

    private String releaseTemMaterialMaterialTexture(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialTexture = temMaterial.getMaterialTexture();
        if ( materialTexture == null ) {
            return null;
        }
        return materialTexture;
    }

    private String releaseTemMaterialContractPurchaserPurchaserCode(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
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

    private String releaseTemMaterialContractPurchaserPurchaserName(Release release) {
        if ( release == null ) {
            return null;
        }
        TemMaterial temMaterial = release.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        Contract contract = temMaterial.getContract();
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
}
