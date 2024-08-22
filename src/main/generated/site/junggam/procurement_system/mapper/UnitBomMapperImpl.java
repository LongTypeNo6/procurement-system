package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.UnitBomDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.UnitBom;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T11:04:28+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UnitBomMapperImpl implements UnitBomMapper {

    @Override
    public UnitBomDTO toDTO(UnitBom unitBom) {
        if ( unitBom == null ) {
            return null;
        }

        UnitBomDTO.UnitBomDTOBuilder unitBomDTO = UnitBomDTO.builder();

        unitBomDTO.unitName( unitBomUnitUnitName( unitBom ) );
        unitBomDTO.unitCode( unitBomUnitUnitCode( unitBom ) );
        unitBomDTO.materialName( unitBomMaterialMaterialName( unitBom ) );
        unitBomDTO.materialCode( unitBomMaterialMaterialCode( unitBom ) );
        unitBomDTO.id( unitBom.getId() );
        unitBomDTO.unitBomQuantity( unitBom.getUnitBomQuantity() );
        unitBomDTO.unitBomProcess( unitBom.getUnitBomProcess() );

        return unitBomDTO.build();
    }

    @Override
    public List<UnitBomDTO> toDTOs(List<UnitBom> unitBoms) {
        if ( unitBoms == null ) {
            return null;
        }

        List<UnitBomDTO> list = new ArrayList<UnitBomDTO>( unitBoms.size() );
        for ( UnitBom unitBom : unitBoms ) {
            list.add( toDTO( unitBom ) );
        }

        return list;
    }

    @Override
    public UnitBom toEntity(UnitBomDTO unitBomDTO) {
        if ( unitBomDTO == null ) {
            return null;
        }

        UnitBom.UnitBomBuilder unitBom = UnitBom.builder();

        unitBom.unit( unitBomDTOToUnit( unitBomDTO ) );
        unitBom.material( unitBomDTOToMaterial( unitBomDTO ) );
        unitBom.id( unitBomDTO.getId() );
        unitBom.unitBomQuantity( unitBomDTO.getUnitBomQuantity() );
        unitBom.unitBomProcess( unitBomDTO.getUnitBomProcess() );

        return unitBom.build();
    }

    private String unitBomUnitUnitName(UnitBom unitBom) {
        if ( unitBom == null ) {
            return null;
        }
        Unit unit = unitBom.getUnit();
        if ( unit == null ) {
            return null;
        }
        String unitName = unit.getUnitName();
        if ( unitName == null ) {
            return null;
        }
        return unitName;
    }

    private String unitBomUnitUnitCode(UnitBom unitBom) {
        if ( unitBom == null ) {
            return null;
        }
        Unit unit = unitBom.getUnit();
        if ( unit == null ) {
            return null;
        }
        String unitCode = unit.getUnitCode();
        if ( unitCode == null ) {
            return null;
        }
        return unitCode;
    }

    private String unitBomMaterialMaterialName(UnitBom unitBom) {
        if ( unitBom == null ) {
            return null;
        }
        Material material = unitBom.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialName = material.getMaterialName();
        if ( materialName == null ) {
            return null;
        }
        return materialName;
    }

    private String unitBomMaterialMaterialCode(UnitBom unitBom) {
        if ( unitBom == null ) {
            return null;
        }
        Material material = unitBom.getMaterial();
        if ( material == null ) {
            return null;
        }
        String materialCode = material.getMaterialCode();
        if ( materialCode == null ) {
            return null;
        }
        return materialCode;
    }

    protected Unit unitBomDTOToUnit(UnitBomDTO unitBomDTO) {
        if ( unitBomDTO == null ) {
            return null;
        }

        Unit.UnitBuilder unit = Unit.builder();

        unit.unitName( unitBomDTO.getUnitName() );
        unit.unitCode( unitBomDTO.getUnitCode() );

        return unit.build();
    }

    protected Material unitBomDTOToMaterial(UnitBomDTO unitBomDTO) {
        if ( unitBomDTO == null ) {
            return null;
        }

        Material.MaterialBuilder material = Material.builder();

        material.materialName( unitBomDTO.getMaterialName() );
        material.materialCode( unitBomDTO.getMaterialCode() );

        return material.build();
    }
}
