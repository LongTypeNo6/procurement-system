package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.dto.UnitMaterialDTO;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.UnitMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-22T11:31:13+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UnitMapperImpl implements UnitMapper {

    @Override
    public UnitDTO toDTO(Unit unit) {
        if ( unit == null ) {
            return null;
        }

        UnitDTO.UnitDTOBuilder unitDTO = UnitDTO.builder();

        unitDTO.unitCode( unit.getUnitCode() );
        unitDTO.unitName( unit.getUnitName() );
        unitDTO.unitStand( unit.getUnitStand() );
        unitDTO.unitTexture( unit.getUnitTexture() );
        unitDTO.unitDrawFile( unit.getUnitDrawFile() );
        unitDTO.unitEtcFile( unit.getUnitEtcFile() );
        unitDTO.unitRegDate( unit.getUnitRegDate() );
        unitDTO.unitModDate( unit.getUnitModDate() );
        unitDTO.unitMaterials( unitMaterialSetToUnitMaterialDTOSet( unit.getUnitMaterials() ) );

        return unitDTO.build();
    }

    @Override
    public List<UnitDTO> toDTOs(List<Unit> units) {
        if ( units == null ) {
            return null;
        }

        List<UnitDTO> list = new ArrayList<UnitDTO>( units.size() );
        for ( Unit unit : units ) {
            list.add( toDTO( unit ) );
        }

        return list;
    }

    @Override
    public Unit toEntity(UnitDTO unitDTO) {
        if ( unitDTO == null ) {
            return null;
        }

        Unit.UnitBuilder unit = Unit.builder();

        unit.unitCode( unitDTO.getUnitCode() );
        unit.unitName( unitDTO.getUnitName() );
        unit.unitStand( unitDTO.getUnitStand() );
        unit.unitTexture( unitDTO.getUnitTexture() );
        unit.unitDrawFile( unitDTO.getUnitDrawFile() );
        unit.unitEtcFile( unitDTO.getUnitEtcFile() );
        unit.unitRegDate( unitDTO.getUnitRegDate() );
        unit.unitModDate( unitDTO.getUnitModDate() );
        unit.unitMaterials( unitMaterialDTOSetToUnitMaterialSet( unitDTO.getUnitMaterials() ) );

        return unit.build();
    }

    protected UnitMaterialDTO unitMaterialToUnitMaterialDTO(UnitMaterial unitMaterial) {
        if ( unitMaterial == null ) {
            return null;
        }

        UnitMaterialDTO.UnitMaterialDTOBuilder unitMaterialDTO = UnitMaterialDTO.builder();

        return unitMaterialDTO.build();
    }

    protected Set<UnitMaterialDTO> unitMaterialSetToUnitMaterialDTOSet(Set<UnitMaterial> set) {
        if ( set == null ) {
            return null;
        }

        Set<UnitMaterialDTO> set1 = new LinkedHashSet<UnitMaterialDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UnitMaterial unitMaterial : set ) {
            set1.add( unitMaterialToUnitMaterialDTO( unitMaterial ) );
        }

        return set1;
    }

    protected UnitMaterial unitMaterialDTOToUnitMaterial(UnitMaterialDTO unitMaterialDTO) {
        if ( unitMaterialDTO == null ) {
            return null;
        }

        UnitMaterial.UnitMaterialBuilder unitMaterial = UnitMaterial.builder();

        return unitMaterial.build();
    }

    protected Set<UnitMaterial> unitMaterialDTOSetToUnitMaterialSet(Set<UnitMaterialDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<UnitMaterial> set1 = new LinkedHashSet<UnitMaterial>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UnitMaterialDTO unitMaterialDTO : set ) {
            set1.add( unitMaterialDTOToUnitMaterial( unitMaterialDTO ) );
        }

        return set1;
    }
}
