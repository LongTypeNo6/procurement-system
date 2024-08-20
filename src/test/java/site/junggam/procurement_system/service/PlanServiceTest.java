package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.ProductBomDTO;
import site.junggam.procurement_system.dto.UnitBomDTO;
import site.junggam.procurement_system.entity.UnitBom;

import java.util.List;

@SpringBootTest
public class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @Test
    @Transactional
    public void findUnitBom() {
        List<UnitBomDTO> list = planService.getUnitBomList("BU-00005");
        list.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void findProductBom() {
        ProductBomDTO dto = planService.getProductBomWithUnits(5L);
        System.out.println(dto);
    }
}
