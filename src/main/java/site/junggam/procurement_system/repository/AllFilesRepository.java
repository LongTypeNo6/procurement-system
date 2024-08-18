package site.junggam.procurement_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.junggam.procurement_system.entity.AllFiles;

import java.util.List;

public interface AllFilesRepository extends JpaRepository<AllFiles,Long> {
    List<AllFiles> findByForeignCode(String foreignCode);
}
