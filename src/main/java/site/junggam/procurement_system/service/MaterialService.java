package site.junggam.procurement_system.service;

import org.springframework.data.domain.Page;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.entity.Material;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MaterialService {
    // 자재 등록 메소드
    String insertMaterial(MaterialDTO materialDTO);

    // 자재 수정 메소드
    void updateMaterial(MaterialDTO materialDTO);

    // 자재 삭제 메소드
    void deleteMaterial(String materialCode);

    // 자재 조회 메소드
    Optional<MaterialDTO> getMaterial(String materialCode);

    // 자재 목록 메소드
    //Page<MaterialDTO> getListMaterial(Pageable pageable);
    List<MaterialDTO> getListMaterial();

    // 자재 검색 메소드
    //Page<MaterialDTO> searchMaterial(String type, Pageable pageable);
    List<MaterialDTO> searchMaterial(String keyword);


    //DTO->Entity
//    default Material dtoToEntity(MaterialDTO materialDTO) {
//        Material material = Material.builder()
//                .materialCode(materialDTO.getMaterialCode())
//                .materialName(materialDTO.getMaterialName())
//                .materialStand(materialDTO.getMaterialStand())
//                .materialTexture(materialDTO.getMaterialTexture())
//                .materialDrawFile(materialDTO.getMaterialDrawFile())
//                .materialEtcFile(materialDTO.getMaterialEtcFile())
//                .materialRegDate(materialDTO.getMaterialRegDate())
//                .materialModDate(materialDTO.getMaterialModDate())
//                .materialSafeQuantity(materialDTO.getMaterialSafeQuantity())
//                .build();
//        return material;
//    }

    //Entity->DTO
//    default MaterialDTO entityToDTO(Material material) {
//        MaterialDTO materialDTO = MaterialDTO.builder()
//                .materialCode(material.getMaterialCode())
//                .materialName(material.getMaterialName())
//                .materialStand(material.getMaterialStand())
//                .materialTexture(material.getMaterialTexture())
//                .materialDrawFile(material.getMaterialDrawFile())
//                .materialEtcFile(material.getMaterialEtcFile())
//                .materialRegDate(material.getMaterialRegDate())
//                .materialModDate(material.getMaterialModDate())
//                .materialSafeQuantity(material.getMaterialSafeQuantity())
//                .build();
//        return materialDTO;
//    }


}
