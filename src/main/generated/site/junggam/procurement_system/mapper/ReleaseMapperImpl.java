package site.junggam.procurement_system.mapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Release;
import site.junggam.procurement_system.entity.TemMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-19T12:08:28+0900",
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

        releaseDTO.procurementPlanDeadLine( releaseProcurementPlanProcurementPlanDeadLine( release ) );
        Integer procurementPlanQuantity = releaseProcurementPlanProcurementPlanQuantity( release );
        if ( procurementPlanQuantity != null ) {
            releaseDTO.procurementPlanQuantity( procurementPlanQuantity );
        }
        releaseDTO.materialCode( releaseProcurementPlanTemMaterialMaterialCode( release ) );
        releaseDTO.materialName( releaseProcurementPlanTemMaterialMaterialName( release ) );
        releaseDTO.materialStand( releaseProcurementPlanTemMaterialMaterialStand( release ) );
        releaseDTO.materialTexture( releaseProcurementPlanTemMaterialMaterialTexture( release ) );
        releaseDTO.purchaserCode( releaseProcurementPlanTemMaterialContractPurchaserPurchaserCode( release ) );
        releaseDTO.purchaserName( releaseProcurementPlanTemMaterialContractPurchaserPurchaserName( release ) );
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

    private LocalDateTime releaseProcurementPlanProcurementPlanDeadLine(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        LocalDateTime procurementPlanDeadLine = procurementPlan.getProcurementPlanDeadLine();
        if ( procurementPlanDeadLine == null ) {
            return null;
        }
        return procurementPlanDeadLine;
    }

    private Integer releaseProcurementPlanProcurementPlanQuantity(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        Integer procurementPlanQuantity = procurementPlan.getProcurementPlanQuantity();
        if ( procurementPlanQuantity == null ) {
            return null;
        }
        return procurementPlanQuantity;
    }

    private String releaseProcurementPlanTemMaterialMaterialCode(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialCode = temMaterial.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    private String releaseProcurementPlanTemMaterialMaterialName(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialName = temMaterial.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String releaseProcurementPlanTemMaterialMaterialStand(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialStand = temMaterial.getMaterialStand();
        if ( materialStand == null ) {
            return null;
        }
        return materialStand;
    }

    private String releaseProcurementPlanTemMaterialMaterialTexture(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
        if ( temMaterial == null ) {
            return null;
        }
        String materialTexture = temMaterial.getMaterialTexture();
        if ( materialTexture == null ) {
            return null;
        }
        return materialTexture;
    }

    private String releaseProcurementPlanTemMaterialContractPurchaserPurchaserCode(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
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

    private String releaseProcurementPlanTemMaterialContractPurchaserPurchaserName(Release release) {
        if ( release == null ) {
            return null;
        }
        ProcurementPlan procurementPlan = release.getProcurementPlan();
        if ( procurementPlan == null ) {
            return null;
        }
        TemMaterial temMaterial = procurementPlan.getTemMaterial();
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
