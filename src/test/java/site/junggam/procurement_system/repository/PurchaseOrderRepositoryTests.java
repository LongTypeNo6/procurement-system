package site.junggam.procurement_system.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PurchaseOrderRepositoryTests {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Test
    public void insertPurchaseOrder(){
        IntStream.rangeClosed(1,9).forEach(i->{
            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .purchaseOrderCode("PURC-240813-001-00"+i)
                    .purchaseOrderDate(LocalDateTime.now())
                    .purchaseOrderMemo("발주비고 입니다"+i)
                    .procurementPlan(ProcurementPlan.builder().procurementPlanCode("PROC-240813-001-00"+i).build())
                    .purchaseOrderStatus(PurchaseOrderStatus.PENDING)
                    .build();
            purchaseOrderRepository.save(purchaseOrder);
        });
        IntStream.rangeClosed(10,20).forEach(i->{
            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .purchaseOrderCode("PURC-240813-001-0"+i)
                    .purchaseOrderDate(LocalDateTime.now())
                    .purchaseOrderMemo("발주비고 입니다"+i)
                    .procurementPlan(ProcurementPlan.builder().procurementPlanCode("PROC-240813-001-0"+i).build())
                    .purchaseOrderStatus(PurchaseOrderStatus.PENDING)
                    .build();
            purchaseOrderRepository.save(purchaseOrder);
        });
    }

    @Test
    @Transactional
    public void getPurchaseOrder(){
        String purchaseOrderCode = "PO1";
        System.out.println("아래에 표시");
        Optional<PurchaseOrder> purchaseOrder=purchaseOrderRepository.findById(purchaseOrderCode);
        if(purchaseOrder.isPresent()){
//            System.out.println(purchaseOrder.get().getPurchaseOrderStatus());
//            System.out.println(purchaseOrder.get().getProcurementPlan().getProcurementPlanCode());
//            System.out.println(purchaseOrder.get().getProcurementPlan().getTemMaterial());
//            System.out.println(purchaseOrder.get().getProcurementPlan().getTemMaterial().getContract());
            System.out.println(purchaseOrder.get());
        }
    }

}
