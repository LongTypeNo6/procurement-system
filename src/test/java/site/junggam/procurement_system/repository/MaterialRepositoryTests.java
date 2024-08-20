package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.TemMaterial;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class MaterialRepositoryTests {

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    public void insertMaterialTest() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            Material material = Material.builder()
                    .materialCode("BM-0000" + i)
                    .materialName("자재이름" + i)
                    .materialStand(i + "cm")
                    .materialTexture("플라스틱" + i)
                    .materialDrawFile("도면파일경로" + i)
                    .materialEtcFile("다른파일경로" + i)
                    .materialRegDate(LocalDateTime.now())
                    .materialModDate(LocalDateTime.now())
                    .materialSafeQuantity(i)
                    .build();
            materialRepository.save(material);
        });
        IntStream.rangeClosed(10, 20).forEach(i -> {
            Material material = Material.builder()
                    .materialCode("BM-000" + i)
                    .materialName("자재이름" + i)
                    .materialStand(i + "cm")
                    .materialTexture("플라스틱" + i)
                    .materialDrawFile("도면파일경로" + i)
                    .materialEtcFile("다른파일경로" + i)
                    .materialRegDate(LocalDateTime.now())
                    .materialModDate(LocalDateTime.now())
                    .materialSafeQuantity(i)
                    .build();
            materialRepository.save(material);
        });
    }
}
