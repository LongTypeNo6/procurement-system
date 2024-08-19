//package site.junggam.procurement_system.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import site.junggam.procurement_system.entity.*;
//
//import java.time.LocalDateTime;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class DummydataTest {
//    @Autowired
//    private TemMaterialRepository temMaterialRepository;
//    @Autowired
//    private PurchaserRepository purchaserRepository;
//    @Autowired
//    private ContractRepository contractRepository;
//    @Autowired
//    private ProcurementPlanRepository procurementPlanRepository;
//    @Autowired
//    private PurchaseOrderRepository purchaseOrderRepository;
//
//
//    @Test
//    public void insertDummy() {
//        IntStream.rangeClosed(1,20).forEach(i->{
//            TemMaterial temMaterial = TemMaterial.builder()
//                    .materialCode("MATE-"+i)
//                    .materialName("자재이름"+i)
//                    .materialStand(i+"cm")
//                    .materialTexture("플라스틱"+i)
//                    .materialDrawFile("도면파일경로"+i)
//                    .materialEtcFile("다른파일경로"+i)
//                    .materialRegDate(LocalDateTime.now())
//                    .materialModDate(LocalDateTime.now())
//                    .materialSafeQuantity(i)
//                    .build();
//            temMaterialRepository.save(temMaterial);
//        });
//        Purchaser purchaser1 = Purchaser.builder()
//                .purchaserCode("PCC-"+1)
//                .purchaserName("정감주식회사")
//                .purchaserPresident("양현일")
//                .purchaserAddress("경기도 수원시 mit 301호")
//                .purchaserCategory("프로그래밍 외")
//                .purchaserType("교육")
//                .purchaserTel("010-1234-5678")
//                .purchaserFax("031-123-4567")
//                .purchaserEmail("junggam@aaa.com")
//                .purchaserManager("김나현")
//                .purchaserManagerTel("010-7184-7380")
//                .purchaserManagerFax("031-222-7380")
//                .purchaserManagerEmail("nahyeon@aaa.com")
//                .build();
//        purchaserRepository.save(purchaser1);
//        IntStream.rangeClosed(2,20).forEach(i->{
//            Purchaser purchaser = Purchaser.builder()
//                    .purchaserCode("PCC-"+i)
//                    .purchaserName("회사명"+i)
//                    .purchaserPresident("이름은사장님"+i)
//                    .purchaserAddress("경기도 수원시 "+i+"번지")
//                    .purchaserCategory("기계부품 외 "+i+"개")
//                    .purchaserType("제조업 외 "+i+"개")
//                    .purchaserTel("010-1111-"+i)
//                    .purchaserFax("031-111-"+i)
//                    .purchaserEmail("회사명"+i+"@aaa.com")
//                    .purchaserManager("이름은담당자"+i)
//                    .purchaserManagerTel("010-2222-"+i)
//                    .purchaserManagerFax("031-222-"+i)
//                    .purchaserManagerEmail("담당자"+i+"@aaa.com")
//                    .build();
//            purchaserRepository.save(purchaser);
//        });
//        IntStream.rangeClosed(1,20).forEach(i -> {
//            Contract contract = Contract.builder()
//                    .contractCode("CC-"+i)
//                    .contractFile("계약서경로"+i)
//                    .contractLeadTime(i)
//                    .contractPrice((double)i)
//                    .temMaterial(TemMaterial.builder().materialCode("MATE-"+i).build())
//                    .purchaser(Purchaser.builder().purchaserCode("PCC-"+i).build())
//                    .build();
//            contractRepository.save(contract);
//        });
//        IntStream.rangeClosed(1,20).forEach(i->{
//            ProcurementPlan procurementPlan = ProcurementPlan.builder()
//                    .procurementPlanCode("PPC-"+i)
//                    .procurementPlanDeadLine(LocalDateTime.now())
//                    .procurementPlantRegDate(LocalDateTime.now())
//                    .procurementPlanQuantity(i)
//                    .temMaterial(TemMaterial.builder().materialCode("MATE-"+i).build())
//                    .build();
//            procurementPlanRepository.save(procurementPlan);
//        });
//        IntStream.rangeClosed(1,20).forEach(i->{
//            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
//                    .purchaseOrderCode("PO"+i)
//                    .purchaseOrderDate(LocalDateTime.now())
//                    .purchaseOrderMemo("발주비고 입니다"+i)
//                    .procurementPlan(ProcurementPlan.builder().procurementPlanCode("PPC-"+i).build())
//                    .purchaseOrderStatus(PurchaseOrderStatus.PENDING)
//                    .build();
//            purchaseOrderRepository.save(purchaseOrder);
//        });
//    }
//}
