package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

public interface UnitBomRepository extends JpaRepository<UnitBom, Long> {

    @EntityGraph(attributePaths = {"unit"}, type = EntityGraph.EntityGraphType.FETCH)
    List<UnitBom> findByunit(Unit unit);
}
