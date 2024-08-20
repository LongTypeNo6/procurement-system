package site.junggam.procurement_system.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.entity.TemMaterial;

import java.util.List;

@SpringBootTest
public class TemMaterialTests {
    
    @Autowired
    private TemMaterialService service;
    
//    @Test
//    public void findAll() {
//        List<TemMaterialDTO> list=service.getTemMaterialList("1");
//        System.out.println("여기입니다"+list);
//    }
}
