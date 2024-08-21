package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.ContractDTO;
import site.junggam.procurement_system.entity.Contract;

@Mapper(componentModel = "spring", uses = {MaterialMapper.class, PurchaserMapper.class})
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "material", target = "materialDTO")
    @Mapping(source = "purchaser", target = "purchaserDTO")
    ContractDTO toDTO(Contract contract);

    @Mapping(source = "materialDTO", target = "material")
    @Mapping(source = "purchaserDTO", target = "purchaser")
    Contract toEntity(ContractDTO contractDTO);
}
