package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

public interface UnitService {
    // 유닛 등록
    String insertUnit(UnitDTO unitDTO);

    // 유닛 수정
    void updateUnit(UnitDTO unitDTO);

    // 유닛 삭제
    boolean deleteUnit(String unitCode);

    // 유닛 조회
    Unit getUnit(String unitCode);

    // 유닛 목록 조회
    List<Unit> getListUnit();


    //엔티티->디티오, 디티오->엔티티 변환 default 메서드 작성..
    //DTO->Entity
    default Unit dtoToEntity(UnitDTO unitDTO) {
        Unit unit = Unit.builder()
                .unitCode(unitDTO.getUnitCode())
                .unitName(unitDTO.getUnitName())
                .unitStand(unitDTO.getUnitStand())
                .unitTexture(unitDTO.getUnitTexture())
                .unitDrawFile(unitDTO.getUnitDrawFile())
                .unitEtcFile(unitDTO.getUnitEtcFile())
                .unitRegDate(unitDTO.getUnitRegDate())
                .unitModDate(unitDTO.getUnitModDate())
                //.product(Product.builder().productCode("BP-00001").build())
                //.product(Product.builder().productCode("BM-00001").build())
                .build();
        return null;
    }
    //Entity->DTO
    default UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .unitStand(unit.getUnitStand())
                .unitDrawFile(unit.getUnitDrawFile())
                .unitEtcFile(unit.getUnitEtcFile())
                .unitRegDate(unit.getUnitRegDate())
                .unitModDate(unit.getUnitModDate())
                //.productCode(productDTO.getProductCode())
                //.meterialCode("BM-00001")
                .build();
        return null;
    }

}
