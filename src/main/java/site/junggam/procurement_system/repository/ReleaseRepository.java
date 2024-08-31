package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Release;

public interface ReleaseRepository extends JpaRepository<Release, String> , QuerydslPredicateExecutor<Release> {
}
