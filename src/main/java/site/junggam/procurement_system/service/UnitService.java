package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    // 유닛 등록
    String insertUnit(UnitDTO unitDTO, List<String> materialCodes);

    // 유닛 수정
    void updateUnit(UnitDTO unitDTO);

    // 유닛 삭제
    void deleteUnit(String unitCode);

    // 유닛 조회
    Optional<UnitDTO> getUnit(String unitCode);

    // 유닛 목록 조회
    List<UnitDTO> getListUnit();

    // 유닛 검색
    List<UnitDTO> searchUnit(String keyword);

    // UnitCode로 UnitDTO를 변환하는 메소드
    //Optional<UnitDTO> getUnitByCode(String unitCode);

    //엔티티->디티오, 디티오->엔티티 변환 default 메서드 작성..
    //DTO->Entity
//    default Unit dtoToEntity(UnitDTO unitDTO) {
//        Unit unit = Unit.builder()
//                .unitCode(unitDTO.getUnitCode())
//                .unitName(unitDTO.getUnitName())
//                .unitStand(unitDTO.getUnitStand())
//                .unitTexture(unitDTO.getUnitTexture())
//                .unitDrawFile(unitDTO.getUnitDrawFile())
//                .unitEtcFile(unitDTO.getUnitEtcFile())
//                .unitRegDate(unitDTO.getUnitRegDate())
//                .unitModDate(unitDTO.getUnitModDate())
//                .build();
//        return unit;
//    }
    //Entity->DTO
//    default UnitDTO entityToDTO(Unit unit) {
//        UnitDTO unitDTO = UnitDTO.builder()
//                .unitCode(unit.getUnitCode())
//                .unitName(unit.getUnitName())
//                .unitStand(unit.getUnitStand())
//                .unitTexture(unit.getUnitTexture())
//                .unitDrawFile(unit.getUnitDrawFile())
//                .unitEtcFile(unit.getUnitEtcFile())
//                .unitRegDate(unit.getUnitRegDate())
//                .unitModDate(unit.getUnitModDate())
//                .unitCode(unit.getProduct() != null ? unit.getProduct().getProductCode() : null)
//                .materialCode(unit.getMaterial() != null ? unit.getMaterial().getMaterialCode() : null)
//                .build();
//        return unitDTO;
//    }

}
