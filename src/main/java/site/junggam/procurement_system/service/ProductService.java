package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    // 제품 등록
    String insertProduct(ProductDTO productDTO, List<String> unitCodes);

    // 제품 수정
    void updateProduct(String productCode, ProductDTO productDTO, List<String> unitCodes);

    // 제품 삭제
    void deleteProduct(String productCode);

    // 제품 조회
    Optional<ProductDTO> getProduct(String productCode);

    // 제품 목록 조회
    List<ProductDTO> getListProduct();

    // 제품 검색
    List<ProductDTO> searchProduct(String keyword);

}
