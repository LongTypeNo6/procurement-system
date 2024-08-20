package site.junggam.procurement_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    UnitDTO toDTO(Unit unit);

    List<UnitDTO> toDTOs(List<Unit> units);

    Unit toEntity(UnitDTO unitDTO);
}
