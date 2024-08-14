package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.ProductUnitDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.ProductUnit;
import site.junggam.procurement_system.entity.ProductUnitId;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.repository.ProductRepository;
import site.junggam.procurement_system.repository.ProductUnitRepository;
import site.junggam.procurement_system.repository.UnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입 : @NonNull or final만 생성자로 만든다.
@Transactional
public class ProductUnitServiceImpl implements ProductUnitService {
    private final ProductUnitRepository productUnitRepository;
    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;


    @Override
    public void insertProductUnit(ProductUnitDTO productUnitDTO) {
        ProductUnit productUnit = convertToEntity(productUnitDTO);
        productUnitRepository.save(productUnit);
    }

    @Override
    public void updateProductUnit(ProductUnitDTO productUnitDTO) {
        ProductUnitId id = new ProductUnitId(productUnitDTO.getProductCode(), productUnitDTO.getUnitCode());
        if (productUnitRepository.existsById(id)) {
            ProductUnit productUnit = convertToEntity(productUnitDTO);
            productUnitRepository.save(productUnit);
        }
    }

    @Override
    public void deleteProductUnit(String productCode, String unitCode) {
        productUnitRepository.deleteById(new ProductUnitId(productCode, unitCode));
    }

    @Override
    public Optional<ProductUnitDTO> getProductUnit(String productCode, String unitCode) {
        return Optional.empty();
    }

    @Override
    public List<ProductUnitDTO> getListProductUnit() {
        return productUnitRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductUnitDTO> searchProductUnit(String keyword) {
        return getListProductUnit();
    }



    private ProductUnitDTO convertToDTO(ProductUnit productUnit) {
        // Convert ProductUnit entity to ProductUnitDTO
        return ProductUnitDTO.builder()
                .productCode(productUnit.getProduct().getProductCode())
                .unitCode(productUnit.getUnit().getUnitCode())
                .build();
    }

    private ProductUnit convertToEntity(ProductUnitDTO productUnitDTO) {
        // Convert ProductUnitDTO to ProductUnit entity
        Product product = productRepository.findById(productUnitDTO.getProductCode()).orElse(null);
        Unit unit = unitRepository.findById(productUnitDTO.getUnitCode()).orElse(null);
        return ProductUnit.builder()
                .productUnitId(new ProductUnitId(productUnitDTO.getProductCode(), productUnitDTO.getUnitCode()))
                .product(product)
                .unit(unit)
                .build();
    }

}
