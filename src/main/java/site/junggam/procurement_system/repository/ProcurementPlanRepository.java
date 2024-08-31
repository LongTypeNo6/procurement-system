package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.*;

import java.util.List;

//CYH : 24.08.29 수정
public interface ProcurementPlanRepository extends JpaRepository<ProcurementPlan, String> , QuerydslPredicateExecutor<ProcurementPlan> {

    @EntityGraph(attributePaths = {"material"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ProcurementPlan> findByMaterial(Material material);

    @EntityGraph(attributePaths = {"productionPlan"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ProcurementPlan> findByProductionPlan(ProductionPlan productionPlan);

    //CYH : 24.08.29 추가
    //SELECT max(tbl_procurement_plan.procurement_plan_code) FROM tbl_procurement_plan WHERE tbl_procurement_plan.procurement_plan_code Like "PROC-240813%";
    @Query("SELECT MAX(p.procurementPlanCode) FROM ProcurementPlan p WHERE p.procurementPlanCode LIKE :dateCode%")
    String findLastIdOfDate(@Param("dateCode") String dateCode);
}
