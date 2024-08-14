package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.UnitMaterialDTO;

import java.util.List;
import java.util.Optional;

public interface UnitMaterialService {

    // (유닛+자재) 등록 메서드
    void insertUnitMaterial(UnitMaterialDTO unitMaterialDTO);

    // (유닛+자재) 수정 메서드
    void updateUnitMaterial(UnitMaterialDTO unitMaterialDTO);

    // (유닛+자재) 삭제 메서드
    void deleteUnitMaterial(String unitCode, String materialCode);

    // (유닛+자재) 조회 메서드
    Optional<UnitMaterialDTO> getUnitMaterial(String unitCode, String materialCode);

    // (유닛+자재) 목록 조회 메서드
    List<UnitMaterialDTO> getListUnitMaterial();

    // (유닛+자재) 검색 메서드
    List<UnitMaterialDTO> searchUnitMaterial(String keyword);

}
