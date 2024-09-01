package site.junggam.procurement_system.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.ContractDTO;
import site.junggam.procurement_system.entity.Contract;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-01T18:38:55+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ContractMapperImpl implements ContractMapper {

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private PurchaserMapper purchaserMapper;

    @Override
    public ContractDTO toDTO(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractDTO.ContractDTOBuilder contractDTO = ContractDTO.builder();

        contractDTO.materialDTO( materialMapper.toDTO( contract.getMaterial() ) );
        contractDTO.purchaserDTO( purchaserMapper.toDto( contract.getPurchaser() ) );
        contractDTO.contractCode( contract.getContractCode() );
        contractDTO.contractFile( contract.getContractFile() );
        contractDTO.contractMemo( contract.getContractMemo() );
        contractDTO.contractPrice( contract.getContractPrice() );
        contractDTO.contractLeadTime( contract.getContractLeadTime() );
        contractDTO.contractDate( contract.getContractDate() );

        return contractDTO.build();
    }

    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        if ( contractDTO == null ) {
            return null;
        }

        Contract.ContractBuilder contract = Contract.builder();

        contract.material( materialMapper.toEntity( contractDTO.getMaterialDTO() ) );
        contract.purchaser( purchaserMapper.toEntity( contractDTO.getPurchaserDTO() ) );
        contract.contractCode( contractDTO.getContractCode() );
        contract.contractFile( contractDTO.getContractFile() );
        contract.contractPrice( contractDTO.getContractPrice() );
        contract.contractLeadTime( contractDTO.getContractLeadTime() );
        contract.contractMemo( contractDTO.getContractMemo() );
        contract.contractDate( contractDTO.getContractDate() );

        return contract.build();
    }
}
