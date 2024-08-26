package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.*;
import site.junggam.procurement_system.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;
    private final ProductBomRepository productBomRepository;
    private final ProductBomMapper productBomMapper;
    private final UnitBomRepository unitBomRepository;
    private final UnitBomMapper unitBomMapper;
    private final ProductionPlanRepository productionPlanRepository;
    private final ProductionPlanMapper productionPlanMapper;
    private final ProcurementPlanRepository procurementPlanRepository;
    private final ProcurementPlanMapper procurementPlanMapper;
    private final EstimateRepository estimateRepository;
    private final EstimateMapper estimateMapper;
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<ProductDTO> getProductListSearching(String keyword) {
        try {
            List<Product> result = productRepository.findByIdAndName(keyword);
            return productMapper.toDTOs(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public List<UnitDTO> getUnitListSearching(String keyword) {
        try {
            List<Unit> result = unitRepository.findByIdAndName(keyword);
            return unitMapper.toDTOs(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    //자재 검색(출고요청용)
    @Override
    public List<MaterialDTO> getMaterialListSearching(String keyword) {
        try {
            List<Material> result = materialRepository.findByIdAndName(keyword);
            return materialMapper.toDTOs(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public List<UnitBomDTO> getUnitBomList(String unitCode) {
        Unit unit = Unit.builder().unitCode(unitCode).build();
        List<UnitBom> result=unitBomRepository.findByunit(unit);
        return unitBomMapper.toDTOs(result);
    }

    @Override
    public List<UnitBomDTO> getUnitBomListWithProductCode(String productCode) {
        Product product = Product.builder().productCode(productCode).build();
        List<ProductBom> entityList =productBomRepository.findByProduct(product);
        List<UnitBomDTO> result=new ArrayList<>();
        entityList.forEach(e->{
            result.addAll(getUnitBomList(e.getUnit().getUnitCode()));
        });
        return result;
    }


    @Override
    public List<ProductBomDTO> getProductBomList(String productCode) {
        Product product = Product.builder().productCode(productCode).build();
        List<ProductBom> entityList =productBomRepository.findByProduct(product);
        List<ProductBomDTO> dtoList = productBomMapper.toDTOs(entityList);
        dtoList.forEach(productBomDTO -> {
            productBomDTO.setUnitBomDTOList(getUnitBomList(productBomDTO.getUnitCode()));
        });
        return dtoList;
    }

    private String generatePlanCode(LocalDateTime localDateTime) {
        // 0. 받아온 날짜 형태 바꾸기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        // 1. 그 날짜의 마지막 코드 가져오기
        String dateCode = "PLAN-"+localDateTime.format(formatter)+"-";
        String lastPlanCode=productionPlanRepository.findLastIdOfDate(dateCode);
        // 2. 만약 마지막 코드가 null이거나 다른 날짜라면 001로 시작
        String newSequence = "001";
        if (lastPlanCode != null) {
            // 3. 마지막 코드의 일련번호 추출
            String lastSequence = lastPlanCode.substring(12);
            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;
            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%03d", nextSequence);
        }
        // 6. 최종 코드를 생성
        return dateCode + newSequence;
    }

    @Override
    public void insertProductionPlan(ProductionPlanDTO productionPlanDTO) {
        String productionPlanCode = generatePlanCode(productionPlanDTO.getProductionPlanDate());
        List<ProcurementPlanDTO> procurementPlanDTOList=productionPlanDTO.getProcurementPlanDTOList();
        productionPlanDTO.setProductionPlanCode(productionPlanCode);
        ProductionPlan productionPlan= productionPlanMapper.toEntity(productionPlanDTO);
        if(productionPlanDTO.getProductCode()!=null){
            productionPlan.setProduct(Product.builder().productCode(productionPlanDTO.getProductCode()).build());
        }else if(productionPlanDTO.getUnitCode()!=null){
            productionPlan.setUnit(Unit.builder().unitCode(productionPlanDTO.getUnitCode()).build());
        }
        productionPlanRepository.save(productionPlan);
        for(int i=0; i<procurementPlanDTOList.size(); i++) {
            ProcurementPlanDTO procurementPlanDTO = procurementPlanDTOList.get(i);
            String procurementPlanCode = "PROC"+productionPlanCode.substring(4)+"-"+String.format("%03d", i+1);
            procurementPlanDTO.setProcurementPlanCode(procurementPlanCode);
            procurementPlanDTO.setProductionPlanCode(productionPlanCode);
            procurementPlanRepository.save(procurementPlanMapper.toEntity(procurementPlanDTO));
        }
    }

    @Override
    public PageResultDTO<ProductionPlanDTO, ProductionPlan> getProductionPlanList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("productionPlanRegDate").descending()); //나주에 바꿀것
            Page<ProductionPlan> result = productionPlanRepository.findAll(pageable);
            Function<ProductionPlan, ProductionPlanDTO> fn = (productionPlan -> {
                ProductionPlanDTO dto = productionPlanMapper.toDTO(productionPlan);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public ProductionPlanDTO getProductionPlan(String productionPlanCode) {
        ProductionPlan productionPlan = productionPlanRepository.findById(productionPlanCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid production plan code: " + productionPlanCode));
        List<ProcurementPlan> procurementPlanList = procurementPlanRepository.findByProductionPlan(productionPlan);
        List<ProcurementPlanDTO> procurementPlanDTOList = procurementPlanMapper.toDTOs(procurementPlanList);
        if (productionPlan.getProduct() == null) {
            // Unit 처리 로직 (이미 작동 중)
            String unitCode = productionPlan.getUnit().getUnitCode();
            List<UnitBom> unitBomList = unitBomRepository.findByunit(Unit.builder().unitCode(unitCode).build());
            for (ProcurementPlanDTO procurementPlanDTO : procurementPlanDTOList) {
                for (UnitBom unitBom : unitBomList) {
                    if (procurementPlanDTO.getMaterialCode().equals(unitBom.getMaterial().getMaterialCode())) {
                        procurementPlanDTO.setBomProcess(unitBom.getUnitBomProcess());
                        procurementPlanDTO.setBomQuantity(unitBom.getUnitBomQuantity());
                        break;
                    }
                }
            }
        } else {
            // Product 처리 로직
            List<ProductBom> productBomList = productBomRepository.findByProduct(productionPlan.getProduct());
            for (ProcurementPlanDTO procurementPlanDTO : procurementPlanDTOList) {
                int totalBomQuantity = 0;
                StringBuilder bomProcesses = new StringBuilder();
                // ProductBom 처리
                for (ProductBom productBom : productBomList) {
                    List<UnitBom> unitBomList = unitBomRepository.findByunit(Unit.builder().unitCode(productBom.getUnit().getUnitCode()).build());
                    for (UnitBom unitBom : unitBomList) {
                        if (procurementPlanDTO.getMaterialCode().equals(unitBom.getMaterial().getMaterialCode())) {
                            int bomQuantity = productBom.getProductBomQuantity() * unitBom.getUnitBomQuantity();
                            totalBomQuantity += bomQuantity;
                            bomProcesses.append(productBom.getProductBomProcess())
                                    .append(" | ")
                                    .append(unitBom.getUnitBomProcess())
                                    .append(" ");
                        }
                    }
                }
                // 설정된 값들을 DTO에 적용
                procurementPlanDTO.setBomProcess(bomProcesses.toString().trim());
                procurementPlanDTO.setBomQuantity(totalBomQuantity);
            }
        }
        ProductionPlanDTO productionPlanDTO = productionPlanMapper.toDTO(productionPlan);
        productionPlanDTO.setProcurementPlanDTOList(procurementPlanDTOList);

        return productionPlanDTO;
    }

    @Override
    public PageResultDTO<ProcurementPlanDTO, ProcurementPlan> getProcurementPlanList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("procurementPlanRegDate").descending()); //나주에 바꿀것
            Page<ProcurementPlan> result = procurementPlanRepository.findAll(pageable);
            Function<ProcurementPlan, ProcurementPlanDTO> fn = (procurementPlan -> {
                ProcurementPlanDTO dto = procurementPlanMapper.toDTO(procurementPlan);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public ProcurementPlanDTO getProcurementPlan(String procurementPlanCode) {
        ProcurementPlan procurementPlan = procurementPlanRepository.findById(procurementPlanCode).get();
        ProcurementPlanDTO procurementPlanDTO = procurementPlanMapper.toDTO(procurementPlan);
        return procurementPlanDTO;
    }

    @Override
    public String resisterEstimate(EstimateDTO estimateDTO) {
        log.info("견적 저장 서비스 연결됨"+estimateDTO);
        String matertialCode=estimateDTO.getMaterialDTO().getMaterialCode();
        String estimateCode = "ESTI-"+matertialCode.substring(3);
        estimateDTO.setEstimateCode(estimateCode);
        estimateDTO.setEstimateFile(estimateCode);
        estimateRepository.save(estimateMapper.toEntity(estimateDTO));
        return estimateCode;
    }

    @Override
    public String resisterContract(ContractDTO contractDTO) {
        log.info("견적 서비스에서 받은값"+contractDTO);
        String purchaserCode=contractDTO.getPurchaserDTO().getPurchaserCode();
        String temCode = "CONT-"+purchaserCode+"-";
        String lastPurchaserCode=contractRepository.findLastIdOfPurchaser(temCode);
        String newSequence = "001";
        if (lastPurchaserCode != null) {
            String lastSequence = lastPurchaserCode.substring(Math.max(0, lastPurchaserCode.length() - 3));
            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;
            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%03d", nextSequence);
        }
        // 6. 최종 코드를 생성
        String contractCode=temCode + newSequence;
        log.info("코드가 뭔데 도대체"+contractCode);
        contractDTO.setContractCode(contractCode);
        contractDTO.setContractFile(contractCode);
        contractRepository.save(contractMapper.toEntity(contractDTO));
        return contractCode;
    }

    @Override
    public String modifyContract(ContractDTO contractDTO) {
        String contractCode=contractDTO.getContractCode();
        Optional<Contract> result
                =contractRepository.findById(contractCode);
        if (result.isPresent()) {
            Contract contract = result.get();
            contract.changeContractMemo(contractDTO.getContractMemo());
            contract.changeContractPrice(contractDTO.getContractPrice());
            contract.changeContractLeadTime(contract.getContractLeadTime());
            contractRepository.save(contract);
        }
        return contractCode;
    }

    @Override
    public String modifyProcurementPlan(ProcurementPlanDTO procurementPlanDTO) {
        String procurementPlanCode=procurementPlanDTO.getProcurementPlanCode();
        Optional<ProcurementPlan> result
                =procurementPlanRepository.findById(procurementPlanCode);
        if (result.isPresent()) {
            ProcurementPlan procurementPlan = result.get();
            procurementPlan.changeProcurementPlanStatus(ProcurementPlanStatus.IN_PROGRESS);
            procurementPlan.changeProcurementPlanQuantity(procurementPlanDTO.getProcurementPlanQuantity());
            procurementPlan.changeProcurementPlanDeadLine(procurementPlanDTO.getProcurementPlanDeadLine());
            procurementPlanRepository.save(procurementPlan);
            //발주서생성
            purchaseOrderRepository.save(PurchaseOrder.builder()
                            .purchaseOrderCode("PURC"+procurementPlanCode.substring(4))
                            .procurementPlan(procurementPlan)
                            .purchaseOrderStatus(PurchaseOrderStatus.PENDING)
                    .build()
            );
        }
        return procurementPlanCode;
    }
}
