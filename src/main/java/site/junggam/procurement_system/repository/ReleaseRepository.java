package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.Release;

public interface ReleaseRepository extends JpaRepository<Release, String> {
}
