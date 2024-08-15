package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.PageRequestDTO;

@SpringBootTest
public class WarehousingServiceTest {

    @Autowired
    private WarehousingService warehousingService;

    @Test
    @Transactional
    public void findOne() {
        String id = "WARE-240813-001-009";
        System.out.println("결과값은 여기"+warehousingService.getWarehousing(id));
    }

    @Test
    @Transactional
    public void findAll() {
        System.out.println("결과값은 이렇게"+warehousingService.getAllWarehousingList(PageRequestDTO.builder().page(1).size(10).build()));
    }
}
