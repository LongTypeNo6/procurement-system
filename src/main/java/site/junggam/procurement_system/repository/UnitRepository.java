package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Unit;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {
    List<Unit> findByUnitNameContaining(String name);
}
