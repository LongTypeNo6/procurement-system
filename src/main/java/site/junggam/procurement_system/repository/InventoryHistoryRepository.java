package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.InventoryHistory;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingStatus;

public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long> {

    @Query("SELECT ih FROM InventoryHistory ih WHERE ih.inventory.materialCode = :materialCode")
    Page<InventoryHistory> findAllByMaterialCode(@Param("materialCode") String materialCode, Pageable pageable);
}
