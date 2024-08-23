package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

public interface UnitBomRepository extends JpaRepository<UnitBom, Long> {

    @EntityGraph(attributePaths = {"unit"}, type = EntityGraph.EntityGraphType.FETCH)
    List<UnitBom> findByunit(Unit unit);

    //SELECT * FROM tbl_unit_bom WHERE tbl_unit_bom.material_code ="BM-00007" AND tbl_unit_bom.unit_code ="BU-00020"
    @Query("SELECT ub FROM UnitBom ub WHERE ub.material.materialCode = :materialCode AND ub.unit.unitCode = :unitCode")
    UnitBom findbyUnitAndMaterial(@Param("materialCode") String dateCode, @Param("unitCode") String unitCode);
}
