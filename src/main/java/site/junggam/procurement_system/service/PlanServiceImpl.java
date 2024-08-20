package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.mapper.MaterialMapper;
import site.junggam.procurement_system.mapper.ProductMapper;
import site.junggam.procurement_system.mapper.UnitMapper;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.ProductRepository;
import site.junggam.procurement_system.repository.UnitRepository;

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
}
