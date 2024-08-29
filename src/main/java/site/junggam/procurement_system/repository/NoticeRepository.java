package site.junggam.procurement_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.junggam.procurement_system.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
//    Page<Notice> findAll(Pageable pageable);
}
