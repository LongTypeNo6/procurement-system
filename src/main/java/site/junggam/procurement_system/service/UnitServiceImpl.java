package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.repository.UnitRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;

    @Override
    public String insertUnit(UnitDTO unitDTO) {
        return "";
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


    //Next 제품코드 자동증가값 생성 메서드
    private String generateNextProductCode() {
        Optional<String> maxProductCode = unitRepository.findAll().stream()
                .map(Unit::getUnitCode)
                .max(String::compareTo);

        String nextCode;
        if (maxProductCode.isPresent()) {
            String maxCode = maxProductCode.get();
            int maxNumber = Integer.parseInt(maxCode.substring(3)); // 'BU-' 제외
            nextCode = String.format("BU-%05d", maxNumber + 1);
        } else {
            nextCode = "BU-00001";
        }

        return nextCode;
    }

}
