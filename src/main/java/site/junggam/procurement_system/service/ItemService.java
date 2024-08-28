package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Unit;

public interface ItemService {

    //자재등록하기
    String materialResister(MaterialDTO materialDTO);

    //자재상세보기
    MaterialDTO materialGet(String materialCode);

    //자재리스트
    PageResultDTO<MaterialDTO, Material> materialList(PageRequestDTO pageRequestDTO);

    //유닛등록하기
    String unitResister(UnitDTO unitDTO);

    //유닛상세보기
    UnitDTO unitGet(String unitCode);

    //유닛리스트
    PageResultDTO<UnitDTO, Unit> unitList(PageRequestDTO pageRequestDTO);

    //제품등록하기
    String productResister(ProductDTO productDTO);

    //제품상세보기
    ProductDTO productGet(String productCode);

    //제품리스트
    PageResultDTO<ProductDTO, Product> productList(PageRequestDTO pageRequestDTO);
}
