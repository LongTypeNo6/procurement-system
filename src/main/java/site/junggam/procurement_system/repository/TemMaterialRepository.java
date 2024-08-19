package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.TemMaterial;

import java.util.List;

public interface TemMaterialRepository extends JpaRepository<TemMaterial,String> {

    @Query("SELECT m FROM TemMaterial m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    Page<TemMaterial> findByIdAndNameWithPage(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM TemMaterial m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    List<TemMaterial> findByIdAndName(@Param("keyword") String keyword);

}
