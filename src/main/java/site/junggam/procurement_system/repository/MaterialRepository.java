package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Material;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, String> {

    @Query("SELECT m FROM Material m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    Page<Material> findByIdAndNameWithPage(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT m FROM Material m WHERE m.materialName LIKE %:keyword% OR m.materialCode LIKE %:keyword%")
    List<Material> findByIdAndName(@Param("keyword") String keyword);
}
