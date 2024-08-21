package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.dto.UnitMaterialDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.ProductRepository;
import site.junggam.procurement_system.repository.UnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 : @NonNull or final만 생성자로 만든다.
@Transactional
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final ProductRepository productRepository; // Add ProductRepository
    private final MaterialRepository materialRepository; // Add MaterialRepository

    @Override
    public String insertUnit(UnitDTO unitDTO, List<String> materialCodes) {
        String newUnitCode = generateNextUnitCode();
        Unit unit = convertToEntity(unitDTO);
        unit.setUnitCode(newUnitCode);
        Set<UnitMaterial> unitMaterials = materialCodes.stream()
                .map(materialCode -> {
                    Material material = materialRepository.findById(materialCode).orElse(null);
                    return new UnitMaterial(new UnitMaterialId(unit.getUnitCode(), materialCode), unit, material);
                })
                .collect(Collectors.toSet());
        unit.setUnitMaterials(unitMaterials);
        Unit saveUnit = unitRepository.save(unit);
        return saveUnit.getUnitCode();

//        String newUnitCode = generateNextUnitCode();
//        unitDTO.setUnitCode(newUnitCode);
//        Unit unit = convertToEntity(unitDTO);
//
//        Unit saveUnit = unitRepository.save(unit);
//        //return saveUnit.getUnitCode();
//        //return unitDTO;
    }

    @Override
    public void updateUnit(String unitCode, UnitDTO unitDTO, List<String> materialCodes) {
//        if (unitRepository.existsById(unitDTO.getUnitCode())) {
//            Unit unit = convertToEntity(unitDTO);
//            unitRepository.save(unit);
//        }

        Unit existingUnit = unitRepository.findById(unitCode).orElse(null);
        if (existingUnit != null) {
            Unit unit = convertToEntity(unitDTO);
            existingUnit.setUnitName(unit.getUnitName());
            existingUnit.setUnitStand(unit.getUnitStand());
            existingUnit.setUnitTexture(unit.getUnitTexture());
            existingUnit.setUnitDrawFile(unit.getUnitDrawFile());
            existingUnit.setUnitEtcFile(unit.getUnitEtcFile());
            existingUnit.setUnitRegDate(unit.getUnitRegDate());
            existingUnit.setUnitModDate(unit.getUnitModDate());

            Set<UnitMaterial> unitMaterials = materialCodes.stream()
                    .map(materialCode -> {
                        Material material = materialRepository.findById(materialCode).orElse(null);
                        return new UnitMaterial(new UnitMaterialId(unitCode, materialCode), existingUnit, material);
                    })
                    .collect(Collectors.toSet());
            existingUnit.setUnitMaterials(unitMaterials);
            unitRepository.save(existingUnit);
        }
    }

    @Override
    public void deleteUnit(String unitCode) {
        unitRepository.deleteById(unitCode);
    }

    @Override
    public Optional<UnitDTO> getUnit(String unitCode) {
//        return unitRepository.findById(unitCode)
//                .map(this::convertToDTO);
        Unit unit = unitRepository.findById(unitCode).orElse(null);
        UnitDTO unitDTO = convertToDTO(unit);
        return Optional.of(unitDTO);
    }

    @Override
    public List<UnitDTO> getListUnit() {
        //return unitRepository.findAll(); // 실제 데이터 반환
        return unitRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UnitDTO> searchUnit(String keyword) {
        //return List.of();
        return getListUnit();
    }


    private UnitDTO convertToDTO(Unit unit) {
        // Convert Unit entity to UnitDTO
        return UnitDTO.builder()
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .unitStand(unit.getUnitStand())
                .unitTexture(unit.getUnitTexture())
                .unitDrawFile(unit.getUnitDrawFile())
                .unitEtcFile(unit.getUnitEtcFile())
                .unitRegDate(unit.getUnitRegDate())
                .unitModDate(unit.getUnitModDate())
                .unitMaterials(unit.getUnitMaterials().stream()
                        .map(um -> UnitMaterialDTO.builder()
                                .unitCode(um.getUnitMaterialId().getUnitCode())
                                .materialCode(um.getUnitMaterialId().getMaterialCode())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    private Unit convertToEntity(UnitDTO unitDTO) {
        // Convert UnitDTO to Unit entity
        return Unit.builder()
                .unitCode(unitDTO.getUnitCode())
                .unitName(unitDTO.getUnitName())
                .unitStand(unitDTO.getUnitStand())
                .unitTexture(unitDTO.getUnitTexture())
                .unitDrawFile(unitDTO.getUnitDrawFile())
                .unitEtcFile(unitDTO.getUnitEtcFile())
                .unitRegDate(unitDTO.getUnitRegDate())
                .unitModDate(unitDTO.getUnitModDate())
                .build();
    }

    //Next 유닛 코드 자동증가값 생성 메서드
    private String generateNextUnitCode() {
        Optional<String> maxUnitCode = unitRepository.findAll().stream()
                .map(Unit::getUnitCode)
                .max(String::compareTo);

        String nextCode;
        if (maxUnitCode.isPresent()) {
            String maxCode = maxUnitCode.get();
            int maxNumber = Integer.parseInt(maxCode.substring(3)); // 'BU-' 제외
            nextCode = String.format("BU-%05d", maxNumber + 1);
        } else {
            nextCode = "BU-00001";
        }

        return nextCode;
    }

}
