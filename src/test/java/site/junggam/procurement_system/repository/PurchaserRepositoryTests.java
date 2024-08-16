package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Purchaser;

import java.util.stream.IntStream;

@SpringBootTest
public class PurchaserRepositoryTests {

    @Autowired
    private PurchaserRepository purchaserRepository;


    @Test
    public void insertOurCompany(){
        Purchaser purchaser = Purchaser.builder()
                .purchaserCode("21")
                .purchaserName("정감주식회사")
                .purchaserPresident("양현일")
                .purchaserAddress("경기도 수원시 mit 301호")
                .purchaserCategory("프로그래밍 외")
                .purchaserType("교육")
                .purchaserTel("010-1234-5678")
                .purchaserFax("031-123-4567")
                .purchaserEmail("junggam@aaa.com")
                .purchaserManager("김나현")
                .purchaserManagerTel("010-7184-7380")
                .purchaserManagerFax("031-222-7380")
                .purchaserManagerEmail("nahyeon@aaa.com")
                .build();
        purchaserRepository.save(purchaser);
    }

    @Test
    public void insertPurchaser(){
        IntStream.rangeClosed(2,9).forEach(i->{
            Purchaser purchaser = Purchaser.builder()
                    .purchaserCode("0"+i)
                    .purchaserName("회사명"+i)
                    .purchaserPresident("이름은사장님"+i)
                    .purchaserAddress("경기도 수원시 "+i+"번지")
                    .purchaserCategory("기계부품 외 "+i+"개")
                    .purchaserType("제조업 외 "+i+"개")
                    .purchaserTel("010-1111-"+i)
                    .purchaserFax("031-111-"+i)
                    .purchaserEmail("회사명"+i+"@aaa.com")
                    .purchaserManager("이름은담당자"+i)
                    .purchaserManagerTel("010-2222-"+i)
                    .purchaserManagerFax("031-222-"+i)
                    .purchaserManagerEmail("담당자"+i+"@aaa.com")
                    .build();
            purchaserRepository.save(purchaser);
        });
        IntStream.rangeClosed(10,20).forEach(i->{
            Purchaser purchaser = Purchaser.builder()
                    .purchaserCode(i+"")
                    .purchaserName("회사명"+i)
                    .purchaserPresident("이름은사장님"+i)
                    .purchaserAddress("경기도 수원시 "+i+"번지")
                    .purchaserCategory("기계부품 외 "+i+"개")
                    .purchaserType("제조업 외 "+i+"개")
                    .purchaserTel("010-1111-"+i)
                    .purchaserFax("031-111-"+i)
                    .purchaserEmail("회사명"+i+"@aaa.com")
                    .purchaserManager("이름은담당자"+i)
                    .purchaserManagerTel("010-2222-"+i)
                    .purchaserManagerFax("031-222-"+i)
                    .purchaserManagerEmail("담당자"+i+"@aaa.com")
                    .build();
            purchaserRepository.save(purchaser);
        });
    }
}
