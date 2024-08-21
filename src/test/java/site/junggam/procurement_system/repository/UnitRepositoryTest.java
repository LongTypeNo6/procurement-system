package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Unit;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class UnitRepositoryTest {

    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void insertUnit() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            Unit unit = Unit.builder()
                    .unitCode("BU-0000" + i)
                    .unitName("유닛이름" + i)
                    .unitStand(i + "cm")
                    .unitTexture("나무" + i)
                    .unitDrawFile("유닛도면파일경로" + i)
                    .unitEtcFile("유닛다른파일경로" + i)
                    .unitRegDate(LocalDateTime.now())
                    .unitModDate(LocalDateTime.now())
                    .build();
            unitRepository.save(unit);
        });
        IntStream.rangeClosed(10, 20).forEach(i -> {
            Unit unit = Unit.builder()
                    .unitCode("BU-000" + i)
                    .unitName("유닛이름" + i)
                    .unitStand(i + "cm")
                    .unitTexture("나무" + i)
                    .unitDrawFile("유닛도면파일경로" + i)
                    .unitEtcFile("유닛다른파일경로" + i)
                    .unitRegDate(LocalDateTime.now())
                    .unitModDate(LocalDateTime.now())
                    .build();
            unitRepository.save(unit);
        });
    }
}
