package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.TemMaterial;
import site.junggam.procurement_system.mapper.MaterialMapper;
import site.junggam.procurement_system.mapper.TemMaterialMapper;
import site.junggam.procurement_system.repository.MaterialRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public String insertMaterial(MaterialDTO materialDTO) {
        String newMaterialCode = generateNextMaterialCode();
        materialDTO.setMaterialCode(newMaterialCode);
        //Material material = dtoToEntity(materialDTO);
        Material material = convertToEntity(materialDTO);
        Material saveMaterial = materialRepository.save(material);
        return saveMaterial.getMaterialCode();
    }

    @Override
    public void updateMaterial(MaterialDTO materialDTO) {
        if (materialRepository.existsById(materialDTO.getMaterialCode())) {
            Material material = convertToEntity(materialDTO);
            materialRepository.save(material);
        }
    }

    @Override
    public void deleteMaterial(String materialCode) {
        materialRepository.deleteById(materialCode);
    }

    @Override
    public Optional<MaterialDTO> getMaterial(String materialCode) {
        return materialRepository.findById(materialCode)
                .map(this::convertToDTO);
    }

    @Override
    public List<MaterialDTO> getListMaterial() { //Page<MaterialDTO> getListMaterial(Pageable pageable)
        //return materialRepository.findAll(); // 실제 데이터 반환
        return materialRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> searchMaterial(String keyword) {
        return getListMaterial();
    }

    //자재 검색(출고요청용)
    @Override
    public List<MaterialDTO> getMaterialListSearching(String keyword) {
        try {
            List<Material> result = materialRepository.findByIdAndName(keyword);
            return materialMapper.toDTOs(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }


    private MaterialDTO convertToDTO(Material material) {
        // Convert Material entity to MaterialDTO
        return MaterialDTO.builder()
                .materialCode(material.getMaterialCode())
                .materialName(material.getMaterialName())
                .materialStand(material.getMaterialStand())
                .materialTexture(material.getMaterialTexture())
                .materialDrawFile(material.getMaterialDrawFile())
                .materialEtcFile(material.getMaterialEtcFile())
                .materialRegDate(material.getMaterialRegDate())
                .materialModDate(material.getMaterialModDate())
                .materialSafeQuantity(material.getMaterialSafeQuantity())
                .build();
    }

    private Material convertToEntity(MaterialDTO materialDTO) {
        // Convert MaterialDTO to Material entity
        return Material.builder()
                .materialCode(materialDTO.getMaterialCode())
                .materialName(materialDTO.getMaterialName())
                .materialStand(materialDTO.getMaterialStand())
                .materialTexture(materialDTO.getMaterialTexture())
                .materialDrawFile(materialDTO.getMaterialDrawFile())
                .materialEtcFile(materialDTO.getMaterialEtcFile())
                .materialRegDate(materialDTO.getMaterialRegDate())
                .materialModDate(materialDTO.getMaterialModDate())
                .materialSafeQuantity(materialDTO.getMaterialSafeQuantity())
                .build();
    }

    //Next 자재코드 자동증가값 생성 메서드
    private String generateNextMaterialCode() {
        Optional<String> maxMaterialCode = materialRepository.findAll().stream()
                .map(Material::getMaterialCode)
                .max(String::compareTo);

        String nextCode;
        if (maxMaterialCode.isPresent()) {
            String maxCode = maxMaterialCode.get();
            int maxNumber = Integer.parseInt(maxCode.substring(3)); // 'BM-' 제외
            nextCode = String.format("BM-%05d", maxNumber + 1);
        } else {
            nextCode = "BM-00001";
        }

        return nextCode;
    }

}
