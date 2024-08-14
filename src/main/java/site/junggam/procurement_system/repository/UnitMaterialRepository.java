package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.UnitMaterial;
import site.junggam.procurement_system.entity.UnitMaterialId;

public interface UnitMaterialRepository extends JpaRepository<UnitMaterial, UnitMaterialId> {

}
