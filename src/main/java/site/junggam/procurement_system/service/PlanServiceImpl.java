package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.*;
import site.junggam.procurement_system.repository.*;

import java.util.List;

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
    public ProductBomDTO getProductBomWithUnits(Long productBomId) {
        ProductBom productBom = productBomRepository.findById(productBomId).orElseThrow();
        List<UnitBom> unitBoms = unitBomRepository.findByunit(Unit.builder().unitCode(productBom.getUnit().getUnitCode()).build());
        return productBomMapper.toDTOWithUnits(productBom, unitBoms);
    }
}
