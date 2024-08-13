package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {

}
