package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.WarehousingHistory;

import java.util.List;

public interface WarehousingHistoryRepository extends JpaRepository<WarehousingHistory, String> {

    @Query("SELECT w FROM WarehousingHistory w WHERE w.warehousing.warehousingCode = :warehousingCode")
    List<WarehousingHistory> findByWarehousingCode(@Param("warehousingCode") String warehousingCode);
}
