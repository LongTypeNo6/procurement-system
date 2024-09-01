package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.DeptNotice;

public interface DeptNoticeRepository extends JpaRepository<DeptNotice, Integer> {
}
