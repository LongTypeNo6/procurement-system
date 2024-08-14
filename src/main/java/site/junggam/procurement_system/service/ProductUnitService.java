package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.ProductUnitDTO;
import site.junggam.procurement_system.entity.ProductUnit;

import java.util.List;
import java.util.Optional;

public interface ProductUnitService {

    // (제품+유닛) 등록 메서드
    void insertProductUnit(ProductUnitDTO productUnitDTO);

    // (제품+유닛) 수정 메서드
    void updateProductUnit(ProductUnitDTO productUnitDTO);

    // (제품+유닛) 삭제 메서드
    void deleteProductUnit(String productCode, String unitCode);

    // (제품+유닛) 조회 메서드
    Optional<ProductUnitDTO> getProductUnit(String productCode, String unitCode);

    // (제품+유닛) 목록 조회 메서드
    List<ProductUnitDTO> getListProductUnit();

    // (제품+유닛) 검색 메서드
    List<ProductUnitDTO> searchProductUnit(String keyword);

}
