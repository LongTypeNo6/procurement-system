package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.InventoryHistory;

public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long> {
}
