package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.PurchaserDTO;
import site.junggam.procurement_system.entity.Purchaser;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaserMapper {
    PurchaserMapper INSTANCE = Mappers.getMapper(PurchaserMapper.class);

    PurchaserDTO toDto(Purchaser purchaser);

    List<PurchaserDTO> toDtos(List<Purchaser> purchasers);

    Purchaser toEntity(PurchaserDTO purchaserDTO);
}
