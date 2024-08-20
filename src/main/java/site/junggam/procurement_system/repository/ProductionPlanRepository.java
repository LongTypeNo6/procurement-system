package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String> {
}