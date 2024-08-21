package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.ProductBom;

import java.util.List;

public interface ProductBomRepository extends JpaRepository<ProductBom, Long> {

    @EntityGraph(attributePaths = {"product"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ProductBom> findByProduct(Product product);
}
