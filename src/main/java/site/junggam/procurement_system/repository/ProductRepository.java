package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
