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
    }
}
