package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Estimate;

public interface EstimateRepository extends JpaRepository<Estimate, String> {

    @Query("SELECT MAX(e.estimateCode) FROM Estimate e WHERE e.estimateCode LIKE :temCode%")
    String findLastIdOfMaterial(@Param("temCode") String temCode);
}
