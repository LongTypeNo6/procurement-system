package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, String> {

}
