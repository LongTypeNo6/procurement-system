package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.Inventory;

@SpringBootTest
public class InventoryServiceTest {
    @Autowired
    private InventoryService inventoryService;

    @Test
    @Transactional
    public void findAll() {
        PageResultDTO <InventoryDTO, Inventory> list=inventoryService.getInventoryList(PageRequestDTO.builder().page(1).size(10).build());
        System.out.println(list);
    }
}
