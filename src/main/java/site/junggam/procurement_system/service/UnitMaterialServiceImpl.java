package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.UnitMaterialDTO;
import site.junggam.procurement_system.entity.UnitMaterial;
import site.junggam.procurement_system.entity.UnitMaterialId;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.UnitMaterialRepository;
import site.junggam.procurement_system.repository.UnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 : @NonNull or final만 생성자로 만든다.
@Transactional
public class UnitMaterialServiceImpl implements UnitMaterialService {
    private final UnitMaterialRepository unitMaterialRepository;
    private final UnitRepository unitRepository;
    private final MaterialRepository materialRepository;


    @Override
    public void insertUnitMaterial(UnitMaterialDTO unitMaterialDTO) {
        UnitMaterial unitMaterial = convertToEntity(unitMaterialDTO);
        unitMaterialRepository.save(unitMaterial);
    }

    @Override
    public void updateUnitMaterial(UnitMaterialDTO unitMaterialDTO) {
        UnitMaterialId id = new UnitMaterialId(unitMaterialDTO.getUnitCode(), unitMaterialDTO.getMaterialCode());
        if (unitMaterialRepository.existsById(id)) {
            UnitMaterial unitMaterial = convertToEntity(unitMaterialDTO);
            unitMaterialRepository.save(unitMaterial);
        }
    }

    @Override
    public void deleteUnitMaterial(String unitCode, String materialCode) {
        unitMaterialRepository.deleteById(new UnitMaterialId(unitCode, materialCode));
    }

    @Override
    public Optional<UnitMaterialDTO> getUnitMaterial(String unitCode, String materialCode) {
        return unitMaterialRepository.findById(new UnitMaterialId(unitCode, materialCode))
                .map(this::convertToDTO);
    }

    @Override
    public List<UnitMaterialDTO> getListUnitMaterial() {
        return unitMaterialRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UnitMaterialDTO> searchUnitMaterial(String keyword) {
        return getListUnitMaterial();
    }



    private UnitMaterialDTO convertToDTO(UnitMaterial unitMaterial) {
        // Convert UnitMaterial entity to UnitMaterialDTO
        return UnitMaterialDTO.builder()
                .unitCode(unitMaterial.getUnitMaterialId().getUnitCode())
                .materialCode(unitMaterial.getUnitMaterialId().getMaterialCode())
                .build();
    }

    private UnitMaterial convertToEntity(UnitMaterialDTO unitMaterialDTO) {
        // Convert UnitMaterialDTO to UnitMaterial entity
        return UnitMaterial.builder()
                .unitMaterialId(new UnitMaterialId(unitMaterialDTO.getUnitCode(), unitMaterialDTO.getMaterialCode()))
                .unit(unitRepository.findById(unitMaterialDTO.getUnitCode()).orElse(null))
                .material(materialRepository.findById(unitMaterialDTO.getMaterialCode()).orElse(null))
                .build();
    }

}
