package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.*;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductBomRepositoryTest {
    @Autowired
    private ProductBomRepository productBomRepository;

    @Test
    public void testSave() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            String productCode = "";
            if(i<10) {
                productCode="BP-0000"+i;
            }else{
                productCode="BP-000"+i;
            }
            Product product=Product.builder().productCode(productCode).build();
            for (int j=1;j<(int)(Math.random()*5+1);j++) {
                int rnum=(int)(Math.random()*20+1);
                String unitCode="";
                if(rnum<10) {
                    unitCode="BU-0000"+rnum;
                }else{
                    unitCode="BU-000"+rnum;
                }
                ProductBom productBom = ProductBom.builder()
                        .product(product)
                        .unit(Unit.builder().unitCode(unitCode).build())
                        .productBomQuantity((int)(Math.random()*9+1))
                        .productBomProcess("제품만드는공정"+i+j)
                        .build();
                productBomRepository.save(productBom);
            }
        });
    }

    @Test
    public void testfindByProduct() {
        List<ProductBom> list = productBomRepository.findByProduct(Product.builder().productCode("BP-00007").build());
        list.forEach(e->{
            System.out.println("결과값은"+e);
        });
        System.out.println("개수는"+list.size());
    }
}
