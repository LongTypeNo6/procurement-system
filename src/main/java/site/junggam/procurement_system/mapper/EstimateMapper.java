package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.EstimateDTO;
import site.junggam.procurement_system.entity.Estimate;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MaterialMapper.class})
public interface EstimateMapper {

    EstimateMapper INSTANCE = Mappers.getMapper(EstimateMapper.class);

    @Mapping(source = "material", target = "materialDTO")
    @Mapping(source = "contract.contractCode", target = "contractCode")
    @Mapping(source = "purchaser.purchaserCode", target = "purchaserCode")
    @Mapping(source = "purchaser.purchaserName", target = "purchaserName")
    EstimateDTO toDTO(Estimate estimate);

    List<EstimateDTO> toDTOs(List<Estimate> estimates);

    @Mapping(source = "contractCode", target = "contract.contractCode")
    @Mapping(source = "purchaserCode", target = "purchaser.purchaserCode")
    @Mapping(source = "materialDTO", target = "material")
    Estimate toEntity(EstimateDTO estimateDTO);
}
