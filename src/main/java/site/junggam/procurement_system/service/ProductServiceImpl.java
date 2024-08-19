package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.junggam.procurement_system.dto.ProductDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor //생성자 의존성 주입
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductUnitRepository productUnitRepository;
    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public String insertProduct(ProductDTO productDTO, List<String> unitCodes) {
        String newProductCode = generateNextProductCode();
        Product product = convertToEntity(productDTO);
        product.setProductCode(newProductCode);

        Set<ProductUnit> productUnits = unitCodes.stream()
                .map(unitCode -> {
                    Unit unit = unitRepository.findById(unitCode).orElse(null);
                    return new ProductUnit(new ProductUnitId(product.getProductCode(), unitCode), product, unit);
                })
                .collect(Collectors.toSet());
        product.setProductUnits(productUnits);
        Product saveProduct = productRepository.save(product);
        return saveProduct.getProductCode();

//        String newProductCode = generateNextProductCode();
//        productDTO.setProductCode(newProductCode);
//        Product product = convertToEntity(productDTO);
//        Product saveProduct = productRepository.save(product);
//        return saveProduct.getProductCode();
    }

    @Override
    public void updateProduct(String productCode, ProductDTO productDTO, List<String> unitCodes) {
//        if (productRepository.existsById(productDTO.getProductCode())) {
//            Product product = convertToEntity(productDTO);
//            productRepository.save(product);
//        }

        Product existingProduct = productRepository.findById(productCode).orElse(null);
        if (existingProduct != null) {
            Product product = convertToEntity(productDTO);
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductStand(product.getProductStand());
            existingProduct.setProductTexture(product.getProductTexture());
            existingProduct.setProductDrawFile(product.getProductDrawFile());
            existingProduct.setProductEtcFile(product.getProductEtcFile());
            existingProduct.setProductRegDate(product.getProductRegDate());
            existingProduct.setProductModDate(product.getProductModDate());

            Set<ProductUnit> productUnits = unitCodes.stream()
                    .map(unitCode -> {
                        Unit unit = unitRepository.findById(unitCode).orElse(null);
                        return new ProductUnit(new ProductUnitId(productCode, unitCode), existingProduct, unit);
                    })
                    .collect(Collectors.toSet());
            existingProduct.setProductUnits(productUnits);
            productRepository.save(existingProduct);
        }
    }

    @Override
    public void deleteProduct(String productCode) {
        productRepository.deleteById(productCode);
    }


    @Override
    public Optional<ProductDTO> getProduct(String productCode) {
//        return productRepository.findById(productCode)
//                .map(this::convertToDTO);
        Product product = productRepository.findById(productCode).orElse(null);
        ProductDTO productDTO = convertToDTO(product);
        return Optional.of(productDTO);
    }

    @Override
    public List<ProductDTO> getListProduct() {
        //return productRepository.findAll(); // 실제 데이터 반환
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProduct(String keyword) {
        //return List.of();
        return getListProduct();
    }

    private ProductDTO convertToDTO(Product product) {
        // Convert Product entity to ProductDTO
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productStand(product.getProductStand())
                .productTexture(product.getProductTexture())
                .productDrawFile(product.getProductDrawFile())
                .productEtcFile(product.getProductEtcFile())
                .productRegDate(product.getProductRegDate())
                .productModDate(product.getProductModDate())
                .productUnits(product.getProductUnits().stream()
                        .map(pu -> ProductUnitDTO.builder()
                                .productCode(pu.getProductUnitId().getProductCode())
                                .unitCode(pu.getProductUnitId().getUnitCode())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    private Product convertToEntity(ProductDTO productDTO) {
        // Convert ProductDTO to Product entity
        return Product.builder()
                .productCode(productDTO.getProductCode())
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice())
                .productStand(productDTO.getProductStand())
                .productTexture(productDTO.getProductTexture())
                .productDrawFile(productDTO.getProductDrawFile())
                .productEtcFile(productDTO.getProductEtcFile())
                .productRegDate(productDTO.getProductRegDate())
                .productModDate(productDTO.getProductModDate())
                .build();
    }

    //Next 제품코드 자동증가값 생성 메서드
    private String generateNextProductCode() {
        Optional<String> maxProductCode = productRepository.findAll().stream()
                .map(Product::getProductCode)
                .max(String::compareTo);

        String nextCode;
        if (maxProductCode.isPresent()) {
            String maxCode = maxProductCode.get();
            int maxNumber = Integer.parseInt(maxCode.substring(3)); // 'BP-' 제외
            nextCode = String.format("BP-%05d", maxNumber + 1);
        } else {
            nextCode = "BP-00001";
        }

        return nextCode;
    }

}
