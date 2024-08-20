package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.*;

import java.util.List;

public interface PlanService {

    //제품 검색(출고요청용)
    List<ProductDTO> getProductListSearching(String keyword);

    //유닛 검색(출고요청용)
    List<UnitDTO> getUnitListSearching(String keyword);

    //자재 검색(출고요청용)
    List<MaterialDTO> getMaterialListSearching(String keyword);

    //유닛코드로 유닛BOM리스트 불러오기
    List<UnitBomDTO> getUnitBomList(String unitCode);

    //프로덕트BOM코드로 찾기
    public ProductBomDTO getProductBomWithUnits(Long productBomId);

}
