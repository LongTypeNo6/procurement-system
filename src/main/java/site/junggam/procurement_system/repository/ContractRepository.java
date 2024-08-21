package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT MAX(c.contractCode) FROM Contract c WHERE c.contractCode LIKE :temCode%")
    String findLastIdOfPurchaser(@Param("temCode") String temCode);
}
