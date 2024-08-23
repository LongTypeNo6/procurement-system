package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Estimate;
import site.junggam.procurement_system.entity.Material;

@SpringBootTest
public class EstimateRepositoryTest {

    @Autowired
    private EstimateRepository estimateRepository;

    @Test
    public void insert() {
        Estimate estimate = Estimate.builder().estimateCode("ESTI-00001-001").material(Material.builder().materialCode("BM-00001").build()).build();
        estimateRepository.save(estimate);
    }
}
