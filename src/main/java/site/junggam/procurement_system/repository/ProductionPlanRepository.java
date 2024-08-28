package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String> , QuerydslPredicateExecutor<ProductionPlan> {

    //SELECT max(tbl_production_plan.production_plan_code) FROM tbl_production_plan WHERE tbl_production_plan.production_plan_code Like "PLAN-240813%";
    @Query("SELECT MAX(p.productionPlanCode) FROM ProductionPlan p WHERE p.productionPlanCode LIKE :dateCode%")
    String findLastIdOfDate(@Param("dateCode") String dateCode);
}