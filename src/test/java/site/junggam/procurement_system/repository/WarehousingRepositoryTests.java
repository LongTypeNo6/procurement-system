package site.junggam.procurement_system.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.Warehousing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class WarehousingRepositoryTests {

    @Autowired
    private WarehousingRepository warehousingRepository;

    @Test
    public void insertWarehousingTest(){
        IntStream.rangeClosed(1,9).forEach(i->{
            Warehousing warehousing = Warehousing.builder()
                    .warehousingCode("WARE-240813-001-00"+i+"-001")
                    .warehousingDate(LocalDateTime.now())
                    .warehousingShipmentSpec("파일경로랑이름"+i)
                    .warehousingSpec("파일경로랑이름"+i)
                    .warehousingResultMemo("메모"+i)
                    .purchaseOrder(PurchaseOrder.builder().purchaseOrderCode("PURC-240813-001-00"+i).build())
                    .build();
            warehousingRepository.save(warehousing);
        });
        IntStream.rangeClosed(10,20).forEach(i->{
            Warehousing warehousing = Warehousing.builder()
                    .warehousingCode("WARE-240813-001-0"+i+"-001")
                    .warehousingDate(LocalDateTime.now())
                    .warehousingShipmentSpec("파일경로랑이름"+i)
                    .warehousingSpec("파일경로랑이름"+i)
                    .warehousingResultMemo("메모"+i)
                    .purchaseOrder(PurchaseOrder.builder().purchaseOrderCode("PURC-240813-001-0"+i).build())
                    .build();
            warehousingRepository.save(warehousing);
        });


    }

    @Test
    public void WarehousingSpecReadTest() {
        List<Warehousing> list = warehousingRepository.findAll();


    }





}
