package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.repository.MaterialRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public String insertMaterial(MaterialDTO materialDTO) {
        String newMaterialCode = generateNextMaterialCode();
        //materialDTO.setMaterialName(materialDTO.getMaterialName());
        materialDTO.setMaterialCode(newMaterialCode);
        Material material = dtoToEntity(materialDTO);
        Material saveMaterial = materialRepository.save(material);
        return saveMaterial.getMaterialCode();
    }

    @Override
    public MaterialDTO updateMaterial(MaterialDTO materialDTO) {
        return null;
    }

    @Override
    public void deleteMaterial(String materialCode) {

    }

    @Override
    public MaterialDTO getMaterial(String materialCode) {
        return null;
    }

    @Override
    public List<Material> getListMaterial() { //Page<MaterialDTO> getListMaterial(Pageable pageable)
        return materialRepository.findAll(); // 실제 데이터 반환
    }

    @Override
    public Page<MaterialDTO> searchMaterial(String type, Pageable pageable) {
        return null;
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
