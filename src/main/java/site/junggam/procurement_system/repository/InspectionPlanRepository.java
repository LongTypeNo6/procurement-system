package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;

import java.util.List;

public interface InspectionPlanRepository extends JpaRepository<InspectionPlan, String> {

    @EntityGraph(attributePaths = {"purchaseOrder"}, type = EntityGraph.EntityGraphType.FETCH)
    List<InspectionPlan> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
