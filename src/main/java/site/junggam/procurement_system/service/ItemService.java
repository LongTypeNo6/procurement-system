package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;

public interface ItemService {

    //자재등록하기
    String materialResister(MaterialDTO materialDTO);

    //자재상세보기
    MaterialDTO materialGet(String materialCode);

    //유닛등록하기
    String unitResister(UnitDTO unitDTO);

    //유닛상세보기
    UnitDTO unitGet(String unitCode);

    //제품등록하기
    String productResister(ProductDTO productDTO);

    //제품상세보기
    ProductDTO productGet(String productCode);

}
