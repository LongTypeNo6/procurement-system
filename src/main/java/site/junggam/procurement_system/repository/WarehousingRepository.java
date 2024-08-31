package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingStatus;

public interface WarehousingRepository extends JpaRepository<Warehousing,String> , QuerydslPredicateExecutor<Warehousing> {

    @Query("SELECT w FROM Warehousing w JOIN w.purchaseOrder p WHERE w.warehousingStatus = :status")
    Page<Warehousing> findAllByStatus(@Param("status") WarehousingStatus status, Pageable pageable);


}
