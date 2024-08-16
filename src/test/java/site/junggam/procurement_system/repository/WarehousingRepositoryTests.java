package site.junggam.procurement_system.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingHistory;
import site.junggam.procurement_system.entity.WarehousingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class WarehousingRepositoryTests {

    @Autowired
    private WarehousingRepository warehousingRepository;

    @Autowired
    private WarehousingHistoryRepository warehousingHistoryRepository;

    @Test
    public void insertWarehousingTest(){
        IntStream.rangeClosed(1,9).forEach(i->{
            Warehousing warehousing = Warehousing.builder()
                    .warehousingCode("WARE-240813-001-00"+i)
                    .purchaseOrder(PurchaseOrder.builder().purchaseOrderCode("PURC-240813-001-00"+i).build())
                    .build();
            warehousingRepository.save(warehousing);
        });
        IntStream.rangeClosed(10,20).forEach(i->{
            Warehousing warehousing = Warehousing.builder()
                    .warehousingCode("WARE-240813-001-0"+i)
                    .purchaseOrder(PurchaseOrder.builder().purchaseOrderCode("PURC-240813-001-0"+i).build())
                    .build();
            warehousingRepository.save(warehousing);
        });
    }


    @Test
    @Transactional
    public void findWarehousingStatusTest(){
        System.out.println(PageRequestDTO.builder().page(1).size(10).build().getPageable(Sort.by("PurchaseOrder.purchaseOrderDate").ascending()));
        System.out.println(warehousingRepository.findAllByStatus(WarehousingStatus.PENDING, PageRequestDTO.builder().page(1).size(10).build().getPageable(Sort.by("purchaseOrder.purchaseOrderDate").ascending())));

    }


    @Test
    @Transactional
    public void findWarehousingHistoryTest(){
        System.out.println("숫자는"+warehousingHistoryRepository.findByWarehousingCode("WARE-240813-001-001").size());
    }

    @Test
    public void saveWarehousingHistoryTest(){
        warehousingHistoryRepository.save(WarehousingHistory.builder()
                        .warehousingHistoryCode("WARE-240813-001-001"+1)
                        .warehousingDate(LocalDateTime.now())
                        .warehousingResultMemo("이런식")
                        .warehousingQuantity(15)
                        .warehousing(Warehousing.builder().warehousingCode("WARE-240813-001-002").build())
                .build());
    }


}
