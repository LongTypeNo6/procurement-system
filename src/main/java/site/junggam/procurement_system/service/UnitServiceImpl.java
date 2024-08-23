package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.dto.UnitMaterialDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.ProductRepository;
import site.junggam.procurement_system.repository.UnitRepository;

import java.time.LocalDateTime;
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
    }

    @Override
    public void updateUnit(String unitCode, UnitDTO unitDTO, List<String> materialCodes) {

        Unit existingUnit = unitRepository.findById(unitCode).orElse(null);
        if (existingUnit != null) {
            //Unit unit = convertToEntity(unitDTO);
            existingUnit.setUnitName(unitDTO.getUnitName());
            existingUnit.setUnitStand(unitDTO.getUnitStand());
            existingUnit.setUnitTexture(unitDTO.getUnitTexture());
            existingUnit.setUnitDrawFile(unitDTO.getUnitDrawFile());
            existingUnit.setUnitEtcFile(unitDTO.getUnitEtcFile());
            //existingUnit.setUnitRegDate(unitDTO.getUnitRegDate());
            existingUnit.setUnitModDate(LocalDateTime.now());

            // 기존의 UnitMaterial을 삭제하고 새로운 UnitMaterial을 추가
            Set<UnitMaterial> newUnitMaterials = materialCodes.stream()
                    .map(materialCode -> {
                        Material material = materialRepository.findById(materialCode).orElse(null);
                        return new UnitMaterial(new UnitMaterialId(unitCode, materialCode), existingUnit, material);
                    })
                    .collect(Collectors.toSet());

            // 기존의 UnitMaterials와 새로 설정한 UnitMaterials를 비교하여 삭제할 항목과 추가할 항목을 설정
            Set<UnitMaterial> currentUnitMaterials = existingUnit.getUnitMaterials();

            // 삭제할 항목 (현재에는 있지만 새로 설정한 목록에는 없는 항목)
            Set<UnitMaterial> toBeRemoved = currentUnitMaterials.stream()
                    .filter(unitMaterial -> !newUnitMaterials.contains(unitMaterial))
                    .collect(Collectors.toSet());

            // 추가할 항목 (현재에는 없지만 새로 설정한 목록에는 있는 항목)
            Set<UnitMaterial> toBeAdded = newUnitMaterials.stream()
                    .filter(unitMaterial -> !currentUnitMaterials.contains(unitMaterial))
                    .collect(Collectors.toSet());

            // 삭제
            existingUnit.getUnitMaterials().removeAll(toBeRemoved);

            // 추가
            existingUnit.getUnitMaterials().addAll(toBeAdded);

            // 저장
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
