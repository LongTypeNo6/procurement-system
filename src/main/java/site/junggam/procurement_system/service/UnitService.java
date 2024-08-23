package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.UnitDTO;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    // 유닛 등록
    String insertUnit(UnitDTO unitDTO, List<String> materialCodes);

    // 유닛 수정
    void updateUnit(String unitCode, UnitDTO unitDTO, List<String> materialCodes);

    // 유닛 삭제
    void deleteUnit(String unitCode);

    // 유닛 조회
    Optional<UnitDTO> getUnit(String unitCode);

    // 유닛 목록 조회
    List<UnitDTO> getListUnit();

    // 유닛 검색
    List<UnitDTO> searchUnit(String keyword);

}
