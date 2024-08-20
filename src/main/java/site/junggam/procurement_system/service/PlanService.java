package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;

import java.util.List;

public interface PlanService {

    //제품 검색(출고요청용)
    List<ProductDTO> getProductListSearching(String keyword);

    //유닛 검색(출고요청용)
    List<UnitDTO> getUnitListSearching(String keyword);

    //자재 검색(출고요청용)
    List<MaterialDTO> getMaterialListSearching(String keyword);


}
