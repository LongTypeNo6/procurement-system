package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.dto.WarehousingHistoryDTO;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingHistory;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehousingHistoryMapper {

    WarehousingHistoryMapper INSTANCE = Mappers.getMapper(WarehousingHistoryMapper.class);

    WarehousingHistoryDTO toDto(WarehousingHistory warehousingHistory);

    List<WarehousingHistoryDTO> toDtos(List<WarehousingHistory> warehousingHistories);

    @Mapping(source = "warehousingCode", target = "warehousing.warehousingCode")
    WarehousingHistory toEntity(WarehousingHistoryDTO warehousingHistoryDTO);
}
