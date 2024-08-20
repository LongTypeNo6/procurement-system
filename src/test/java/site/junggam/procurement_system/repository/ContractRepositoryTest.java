package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Contract;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.TemMaterial;

import java.time.LocalTime;
import java.util.stream.IntStream;

@SpringBootTest
public class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void insertContract() {
        IntStream.rangeClosed(1,9).forEach(i -> {
            Contract contract = Contract.builder()
                    .contractCode("CONT-0"+i+"-00"+i)
                    .contractFile("계약서경로"+i)
                    .contractLeadTime(i)
                    .contractPrice((double)i)
                    .material(Material.builder().materialCode("BM-0000"+i).build())
                    .purchaser(Purchaser.builder().purchaserCode("0"+i).build())
                    .build();
            contractRepository.save(contract);
        });
        IntStream.rangeClosed(10,20).forEach(i -> {
            Contract contract = Contract.builder()
                    .contractCode("CONT-"+i+"-0"+i)
                    .contractFile("계약서경로"+i)
                    .contractLeadTime(i)
                    .contractPrice((double)i)
                    .material(Material.builder().materialCode("BM-000"+i).build())
                    .purchaser(Purchaser.builder().purchaserCode(i+"").build())
                    .build();
            contractRepository.save(contract);
        });
    }
}
