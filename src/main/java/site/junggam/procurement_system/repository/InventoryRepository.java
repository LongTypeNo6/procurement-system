package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Purchaser;

public interface InventoryRepository extends JpaRepository<Inventory, String> , QuerydslPredicateExecutor<Inventory> {

    @Query("SELECT SUM(i.materialQuantity) FROM Inventory i")
    Integer getTotallMaterialQuantity();

    @Query("SELECT SUM(i.contractAvgPrice*i.materialQuantity) FROM Inventory i")
    Double getTotallContractAvgPrice();

    @Query("SELECT i FROM Inventory i ORDER BY (i.materialQuantity * i.contractAvgPrice) DESC")
    Page<Inventory> findAllOrderByTotalPriceDesc(Pageable pageable);

}
