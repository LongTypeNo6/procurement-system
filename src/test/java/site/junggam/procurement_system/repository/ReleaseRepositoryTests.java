package site.junggam.procurement_system.repository;

import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.Release;

import java.time.LocalDateTime;

@SpringBootTest
public class ReleaseRepositoryTests {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Test
    public void testinsert(){
        Release release = Release.builder()
                .releaseCode("RELE-240813-001-001")
                .releaseRequestDept("생산1팀")
                .releaseRequestDate(LocalDateTime.now())
                .releaseDesireDate(LocalDateTime.now())
                .releaseDesireQuantity(10)
                .releaseRequestMemo("출고요청비고")
                .releaseDate(LocalDateTime.now())
                .build();
        releaseRepository.save(release);
    }
}
