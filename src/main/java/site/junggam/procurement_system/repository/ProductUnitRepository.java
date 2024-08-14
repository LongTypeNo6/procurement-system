package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.ProductUnit;
import site.junggam.procurement_system.entity.ProductUnitId;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, ProductUnitId> {

}
