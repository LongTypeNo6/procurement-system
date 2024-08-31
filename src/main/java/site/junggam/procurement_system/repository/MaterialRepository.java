package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, String> , QuerydslPredicateExecutor<Material> {

    //출고검색용
    @Query("SELECT m FROM Material m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    Page<Material> findByIdAndNameWithPage(@Param("keyword") String keyword, Pageable pageable);

    //출고검색용
    @Query("SELECT m FROM Material m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    List<Material> findByIdAndName(@Param("keyword") String keyword);

    //가장 최신 자재 코드 찾는 거
    //SELECT max(material_code) FROM tbl_material;
    @Query("SELECT max(m.materialCode) FROM Material m")
    String findMaxMaterialCode();
}
