package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.entity.Material;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T14:55:51+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public MaterialDTO toDTO(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialDTO.MaterialDTOBuilder materialDTO = MaterialDTO.builder();

        materialDTO.materialCode( material.getMaterialCode() );
        materialDTO.materialName( material.getMaterialName() );
        materialDTO.materialStand( material.getMaterialStand() );
        materialDTO.materialTexture( material.getMaterialTexture() );
        materialDTO.materialDrawFile( material.getMaterialDrawFile() );
        materialDTO.materialEtcFile( material.getMaterialEtcFile() );
        materialDTO.materialRegDate( material.getMaterialRegDate() );
        materialDTO.materialModDate( material.getMaterialModDate() );
        materialDTO.materialSafeQuantity( material.getMaterialSafeQuantity() );
        materialDTO.materialContractStatus( material.getMaterialContractStatus() );

        return materialDTO.build();
    }

    @Override
    public List<MaterialDTO> toDTOs(List<Material> materials) {
        if ( materials == null ) {
            return null;
        }

        List<MaterialDTO> list = new ArrayList<MaterialDTO>( materials.size() );
        for ( Material material : materials ) {
            list.add( toDTO( material ) );
        }

        return list;
    }

    @Override
    public Material toEntity(MaterialDTO materialDTO) {
        if ( materialDTO == null ) {
            return null;
        }

        Material.MaterialBuilder material = Material.builder();

        material.materialCode( materialDTO.getMaterialCode() );
        material.materialName( materialDTO.getMaterialName() );
        material.materialStand( materialDTO.getMaterialStand() );
        material.materialTexture( materialDTO.getMaterialTexture() );
        material.materialDrawFile( materialDTO.getMaterialDrawFile() );
        material.materialEtcFile( materialDTO.getMaterialEtcFile() );
        material.materialRegDate( materialDTO.getMaterialRegDate() );
        material.materialModDate( materialDTO.getMaterialModDate() );
        material.materialSafeQuantity( materialDTO.getMaterialSafeQuantity() );
        material.materialContractStatus( materialDTO.getMaterialContractStatus() );

        return material.build();
    }
}
