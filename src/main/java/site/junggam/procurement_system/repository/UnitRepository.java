package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, String> , QuerydslPredicateExecutor<Unit> {
    List<Unit> findByUnitNameContaining(String name);

    //출고검색용
    @Query("SELECT u FROM Unit u WHERE u.unitName LIKE %:keyword% OR u.unitCode LIKE %:keyword%")
    List<Unit> findByIdAndName(@Param("keyword") String keyword);

    //가장 최신 자재 코드 찾는 거
    //SELECT max(unit_code) FROM tbl_unit;
    @Query("SELECT max(u.unitCode) FROM Unit u")
    String findMaxUnitCode();
}
