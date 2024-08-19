package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.junggam.procurement_system.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

    @Query("SELECT SUM(i.materialQuantity) FROM Inventory i")
    Integer getTotallMaterialQuantity();

    @Query("SELECT AVG(i.contractAvgPrice) FROM Inventory i")
    Double getTotallContractAvgPrice();
}
