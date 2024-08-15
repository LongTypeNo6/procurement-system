package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
