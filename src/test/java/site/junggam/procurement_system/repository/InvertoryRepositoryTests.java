package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.TemMaterial;

@SpringBootTest
public class InvertoryRepositoryTests {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    TemMaterialRepository temMaterialRepository;

    @Test
    @Commit
    @Transactional // 테스트 메서드에 트랜잭션을 적용
    public void insert() {
        // material_code에 해당하는 TemMaterial이 있는지 확인
        for(int i=1;i<21;i++){
            String materialCode = "BM-000";
            if(i<10){
                materialCode +=0;
                materialCode +=i;
            }else {
                materialCode +=i;
            }
            // TemMaterial을 데이터베이스에서 조회
            TemMaterial temMaterial = temMaterialRepository.findById(materialCode)
                    .orElseThrow(() -> new RuntimeException("TemMaterial not found with code: "));
            // Inventory 엔티티 생성 및 저장
            Inventory inventory = Inventory.builder()
                    .temMaterial(temMaterial) // 존재하는 TemMaterial 사용
                    .materialQuantity((int)(Math.random()*100+1))
                    .releaseDesireSumQuantity((int)(Math.random()*100+1))
                    .procurementPlanSumQuantity((int)(Math.random()*100+1))
                    .warehousingSumQuantity((int)(Math.random()*100+1))
                    .contractAvgPrice(Math.random()*100+1)
                    .build();
            inventoryRepository.save(inventory);
        }

    }

    @Test
    public void getQauntity() {
        System.out.println("총량은"+inventoryRepository.getTotallMaterialQuantity());
    }

    @Test
    public void getPrice() {
        System.out.println("총평균단가?"+inventoryRepository.getTotallContractAvgPrice());
    }
}

