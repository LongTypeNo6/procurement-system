package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;

import java.util.List;

public interface PurchaserRepository extends JpaRepository<Purchaser,String> {

    //출고검색용
    @Query("SELECT p FROM Purchaser p WHERE p.purchaserName LIKE %:keyword% OR p.purchaserCode LIKE %:keyword%")
    List<Purchaser> findByIdAndName(@Param("keyword") String keyword);

}
