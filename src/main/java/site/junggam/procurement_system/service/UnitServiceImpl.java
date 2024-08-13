package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.ProductRepository;
import site.junggam.procurement_system.repository.UnitRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 : @NonNull or final만 생성자로 만든다.
@Transactional
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final ProductRepository productRepository; // Add ProductRepository
    private final MaterialRepository materialRepository; // Add MaterialRepository

    @Override
    public String insertUnit(UnitDTO unitDTO) {
        String newUnitCode = generateNextUnitCode();
        //unitDTO.setUnitName(unitDTO.getUnitName());
        unitDTO.setUnitCode(newUnitCode);
        Unit unit = dtoToEntity(unitDTO);

        // Set Product entity
        if (unitDTO.getProductCode() != null) {
            Product product = productRepository.findById(unitDTO.getProductCode())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            unit.setProduct(product);
        }

        // Set Material entity
        if (unitDTO.getMaterialCode() != null) {
            Material material = materialRepository.findById(unitDTO.getMaterialCode())
                    .orElseThrow(() -> new RuntimeException("Material not found"));
            unit.setMaterial(material);
        }

        Unit saveUnit = unitRepository.save(unit);
        return saveUnit.getUnitCode();
    }

    @Override
    public void updateUnit(UnitDTO unitDTO) {

    }

    @Override
    public boolean deleteUnit(String unitCode) {
        return false;
    }

    @Override
    public Unit getUnit(String unitCode) {
        return null;
    }

    @Override
    public List<Unit> getListUnit() {
        return unitRepository.findAll(); // 실제 데이터 반환
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
