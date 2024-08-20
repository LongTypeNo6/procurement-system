package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.entity.TemMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-20T15:22:39+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TemMaterialMapperImpl implements TemMaterialMapper {

    @Override
    public TemMaterialDTO toDTO(TemMaterial temMaterial) {
        if ( temMaterial == null ) {
            return null;
        }

        TemMaterialDTO.TemMaterialDTOBuilder temMaterialDTO = TemMaterialDTO.builder();

        temMaterialDTO.materialCode( temMaterial.getMaterialCode() );
        temMaterialDTO.materialName( temMaterial.getMaterialName() );
        temMaterialDTO.materialStand( temMaterial.getMaterialStand() );
        temMaterialDTO.materialTexture( temMaterial.getMaterialTexture() );
        temMaterialDTO.materialDrawFile( temMaterial.getMaterialDrawFile() );
        temMaterialDTO.materialEtcFile( temMaterial.getMaterialEtcFile() );
        temMaterialDTO.materialRegDate( temMaterial.getMaterialRegDate() );
        temMaterialDTO.materialModDate( temMaterial.getMaterialModDate() );
        temMaterialDTO.materialSafeQuantity( temMaterial.getMaterialSafeQuantity() );

        return temMaterialDTO.build();
    }

    @Override
    public List<TemMaterialDTO> toDTOs(List<TemMaterial> temMaterials) {
        if ( temMaterials == null ) {
            return null;
        }

        List<TemMaterialDTO> list = new ArrayList<TemMaterialDTO>( temMaterials.size() );
        for ( TemMaterial temMaterial : temMaterials ) {
            list.add( toDTO( temMaterial ) );
        }

        return list;
    }

    @Override
    public TemMaterial toEntity(TemMaterialDTO temMaterialDTO) {
        if ( temMaterialDTO == null ) {
            return null;
        }

        TemMaterial.TemMaterialBuilder temMaterial = TemMaterial.builder();

        temMaterial.materialCode( temMaterialDTO.getMaterialCode() );
        temMaterial.materialName( temMaterialDTO.getMaterialName() );
        temMaterial.materialStand( temMaterialDTO.getMaterialStand() );
        temMaterial.materialTexture( temMaterialDTO.getMaterialTexture() );
        temMaterial.materialDrawFile( temMaterialDTO.getMaterialDrawFile() );
        temMaterial.materialEtcFile( temMaterialDTO.getMaterialEtcFile() );
        temMaterial.materialRegDate( temMaterialDTO.getMaterialRegDate() );
        temMaterial.materialModDate( temMaterialDTO.getMaterialModDate() );
        temMaterial.materialSafeQuantity( temMaterialDTO.getMaterialSafeQuantity() );

        return temMaterial.build();
    }
}
