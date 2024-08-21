package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.*;

import java.util.List;

public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, String> {

    @EntityGraph(attributePaths = {"material"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ProcurementPlan> findByMaterial(Material material);

    @EntityGraph(attributePaths = {"productionPlan"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ProcurementPlan> findByProductionPlan(ProductionPlan productionPlan);
}
