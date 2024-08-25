package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {


    //출고검색용
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.productCode LIKE %:keyword%")
    List<Product> findByIdAndName(@Param("keyword") String keyword);

    //가장 최신 자재 코드 찾는 거
    //SELECT max(unit_code) FROM tbl_unit;
    @Query("SELECT max(p.productCode) FROM Product p")
    String findMaxProductCode();
}
