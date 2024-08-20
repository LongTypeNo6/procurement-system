package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @Override
    @Transactional
    public void insertProductionPlan(ProductionPlanDTO productionPlanDTO) {
        productionPlanDTO.setProductionPlanCode(generatePlanCode(productionPlanDTO.getProductionPlanDate()));
        ProductionPlan productionPlan= productionPlanMapper.toEntity(productionPlanDTO);
        if(productionPlanDTO.getProductCode()!=null){
            productionPlan.setProduct(Product.builder().productCode(productionPlanDTO.getProductCode()).build());
        }else if(productionPlanDTO.getUnitCode()!=null){
            productionPlan.setUnit(Unit.builder().unitCode(productionPlanDTO.getUnitCode()).build());
        }
        productionPlanRepository.save(productionPlan);
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
}
