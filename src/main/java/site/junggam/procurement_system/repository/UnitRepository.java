package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, String> {
    List<Unit> findByUnitNameContaining(String name);

    //출고검색용
    @Query("SELECT u FROM Unit u WHERE u.unitName LIKE %:keyword% OR u.unitCode LIKE %:keyword%")
    List<Unit> findByIdAndName(@Param("keyword") String keyword);
}
