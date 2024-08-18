package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReleaseServiceTests {

    @Autowired
    private ReleaseService service;

    @Test
    @Transactional
    public void findbyId() {
        System.out.println("결과는"+service.getReleaseRequest("RELE-240813-001-001"));
    }
}
