package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.PurchaserDTO;
import site.junggam.procurement_system.entity.Purchaser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T10:55:06+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PurchaserMapperImpl implements PurchaserMapper {

    @Override
    public PurchaserDTO toDto(Purchaser purchaser) {
        if ( purchaser == null ) {
            return null;
        }

        PurchaserDTO.PurchaserDTOBuilder purchaserDTO = PurchaserDTO.builder();

        purchaserDTO.purchaserCode( purchaser.getPurchaserCode() );
        purchaserDTO.purchaserName( purchaser.getPurchaserName() );
        purchaserDTO.purchaserAddress( purchaser.getPurchaserAddress() );
        purchaserDTO.purchaserFax( purchaser.getPurchaserFax() );
        purchaserDTO.purchaserEmail( purchaser.getPurchaserEmail() );
        purchaserDTO.purchaserType( purchaser.getPurchaserType() );
        purchaserDTO.purchaserTel( purchaser.getPurchaserTel() );
        purchaserDTO.purchaserCategory( purchaser.getPurchaserCategory() );
        purchaserDTO.purchaserPresident( purchaser.getPurchaserPresident() );
        purchaserDTO.purchaserManager( purchaser.getPurchaserManager() );
        purchaserDTO.purchaserManagerTel( purchaser.getPurchaserManagerTel() );
        purchaserDTO.purchaserManagerEmail( purchaser.getPurchaserManagerEmail() );
        purchaserDTO.purchaserManagerFax( purchaser.getPurchaserManagerFax() );
        purchaserDTO.purchaserMemo( purchaser.getPurchaserMemo() );

        return purchaserDTO.build();
    }

    @Override
    public List<PurchaserDTO> toDtos(List<Purchaser> purchasers) {
        if ( purchasers == null ) {
            return null;
        }

        List<PurchaserDTO> list = new ArrayList<PurchaserDTO>( purchasers.size() );
        for ( Purchaser purchaser : purchasers ) {
            list.add( toDto( purchaser ) );
        }

        return list;
    }

    @Override
    public Purchaser toEntity(PurchaserDTO purchaserDTO) {
        if ( purchaserDTO == null ) {
            return null;
        }

        Purchaser.PurchaserBuilder purchaser = Purchaser.builder();

        purchaser.purchaserCode( purchaserDTO.getPurchaserCode() );
        purchaser.purchaserName( purchaserDTO.getPurchaserName() );
        purchaser.purchaserAddress( purchaserDTO.getPurchaserAddress() );
        purchaser.purchaserFax( purchaserDTO.getPurchaserFax() );
        purchaser.purchaserEmail( purchaserDTO.getPurchaserEmail() );
        purchaser.purchaserType( purchaserDTO.getPurchaserType() );
        purchaser.purchaserTel( purchaserDTO.getPurchaserTel() );
        purchaser.purchaserCategory( purchaserDTO.getPurchaserCategory() );
        purchaser.purchaserPresident( purchaserDTO.getPurchaserPresident() );
        purchaser.purchaserManager( purchaserDTO.getPurchaserManager() );
        purchaser.purchaserManagerTel( purchaserDTO.getPurchaserManagerTel() );
        purchaser.purchaserManagerEmail( purchaserDTO.getPurchaserManagerEmail() );
        purchaser.purchaserManagerFax( purchaserDTO.getPurchaserManagerFax() );
        purchaser.purchaserMemo( purchaserDTO.getPurchaserMemo() );

        return purchaser.build();
    }
}
