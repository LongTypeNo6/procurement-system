package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.entity.UnitBom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class UnitBomRepositoryTest {

    @Autowired
    private UnitBomRepository unitBomRepository;

    @Test
    public void testSave() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            String unitCode = "";
            if(i<10) {
                unitCode="BU-0000"+i;
            }else{
                unitCode="BU-000"+i;
            }
            Unit unit=Unit.builder().unitCode(unitCode).build();
                for (int j=1;j<(int)(Math.random()*5+1);j++) {
                    int rnum=(int)(Math.random()*20+1);
                    String materialCode="";
                    if(rnum<10) {
                        materialCode="BM-0000"+rnum;
                    }else{
                        materialCode="BM-000"+rnum;
                    }
                    UnitBom unitBom = UnitBom.builder()
                            .unit(unit)
                            .material(Material.builder().materialCode(materialCode).build())
                            .unitBomQuantity((int)(Math.random()*20+1))
                            .unitBomProcess("유닛만드는공정"+i+j)
                            .build();
                    unitBomRepository.save(unitBom);
                }
        });
    }

    @Test
    @Transactional
    public void findUnitBomByUnit() {
        List<UnitBom> list = unitBomRepository.findByunit(Unit.builder().unitCode("BU-00010").build());
        list.forEach(e->{
            System.out.println("결과값은"+e+e.getMaterial());
        });
        System.out.println("개수는"+list.size());
    }

}
