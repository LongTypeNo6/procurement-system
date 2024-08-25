package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.Product;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private UnitRepository unitRepository;
    private MaterialRepository materialRepository;

    @Test
    public void insertProduct() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            Product product = Product.builder()
                    .productCode("BP-0000" + i)
                    .productName("제품이름" + i)
                    .productStand(i + "cm")
                    .productTexture("금속" + i)
                    .productDrawFile("제품도면파일경로" + i)
                    .productEtcFile("제품다른파일경로" + i)
                    .productPrice(Math.random()*100+1)
                    .productRegDate(LocalDateTime.now())
                    .productModDate(LocalDateTime.now())
                    .build();
            productRepository.save(product);
        });
        IntStream.rangeClosed(10, 20).forEach(i -> {
            Product product = Product.builder()
                    .productCode("BP-000" + i)
                    .productName("제품이름" + i)
                    .productStand(i + "cm")
                    .productTexture("금속" + i)
                    .productDrawFile("제품도면파일경로" + i)
                    .productEtcFile("제품다른파일경로" + i)
                    .productPrice(Math.random()*100+1)
                    .productRegDate(LocalDateTime.now())
                    .productModDate(LocalDateTime.now())
                    .build();
            productRepository.save(product);
        });
    };

    private String generateCode(String itemCode) {
        String lastCode=null;
        if(itemCode.equals("BM")){
            lastCode=materialRepository.findMaxMaterialCode();
        }else if(itemCode.equals("BU")){
            lastCode=unitRepository.findMaxUnitCode();
        }else if(itemCode.equals("BP")){
            lastCode=productRepository.findMaxProductCode();
        }
        // 2. 만약 마지막 코드가 null이거나 다른 날짜라면 001로 시작
        String newSequence = "00001";
        if (lastCode!= null) {
            // 3. 마지막 코드의 일련번호 추출
            String lastSequence = lastCode.substring(3);
            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;
            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%05d", nextSequence);
        }
        // 6. 최종 코드를 생성
        return itemCode+"-"+ newSequence;
    };

    @Test
    public void findNextCode(){
        String itemCode ="BP";
        System.out.println("다음나올 값은" +generateCode(itemCode));
    };
}
