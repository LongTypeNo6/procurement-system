package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.EstimateDTO;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Estimate;
import site.junggam.procurement_system.entity.Purchaser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T09:37:01+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class EstimateMapperImpl implements EstimateMapper {

    @Override
    public EstimateDTO toDTO(Estimate estimate) {
        if ( estimate == null ) {
            return null;
        }

        EstimateDTO.EstimateDTOBuilder estimateDTO = EstimateDTO.builder();

        estimateDTO.materialDTO( estimate.getMaterial() );
        estimateDTO.contractCode( estimateContractContractCode( estimate ) );
        estimateDTO.purchaserCode( estimatePurchaserPurchaserCode( estimate ) );
        estimateDTO.purchaserName( estimatePurchaserPurchaserName( estimate ) );
        estimateDTO.estimateCode( estimate.getEstimateCode() );
        estimateDTO.estimateFile( estimate.getEstimateFile() );
        estimateDTO.estimateMemo( estimate.getEstimateMemo() );
        estimateDTO.estimateStatus( estimate.getEstimateStatus() );

        return estimateDTO.build();
    }

    @Override
    public List<EstimateDTO> toDTOs(List<Estimate> estimates) {
        if ( estimates == null ) {
            return null;
        }

        List<EstimateDTO> list = new ArrayList<EstimateDTO>( estimates.size() );
        for ( Estimate estimate : estimates ) {
            list.add( toDTO( estimate ) );
        }

        return list;
    }

    @Override
    public Estimate toEntity(EstimateDTO estimateDTO) {
        if ( estimateDTO == null ) {
            return null;
        }

        Estimate.EstimateBuilder estimate = Estimate.builder();

        estimate.material( estimateDTO.getMaterialDTO() );
        estimate.estimateCode( estimateDTO.getEstimateCode() );
        estimate.estimateFile( estimateDTO.getEstimateFile() );
        estimate.estimateMemo( estimateDTO.getEstimateMemo() );
        estimate.estimateStatus( estimateDTO.getEstimateStatus() );

        return estimate.build();
    }

    private String estimateContractContractCode(Estimate estimate) {
        if ( estimate == null ) {
            return null;
        }
        Contract contract = estimate.getContract();
        if ( contract == null ) {
            return null;
        }
        String contractCode = contract.getContractCode();
        if ( contractCode == null ) {
            return null;
        }
        return contractCode;
    }

    private String estimatePurchaserPurchaserCode(Estimate estimate) {
        if ( estimate == null ) {
            return null;
        }
        Purchaser purchaser = estimate.getPurchaser();
        if ( purchaser == null ) {
            return null;
        }
        String purchaserCode = purchaser.getPurchaserCode();
        if ( purchaserCode == null ) {
            return null;
        }
        return purchaserCode;
    }

    private String estimatePurchaserPurchaserName(Estimate estimate) {
        if ( estimate == null ) {
            return null;
        }
        Purchaser purchaser = estimate.getPurchaser();
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
