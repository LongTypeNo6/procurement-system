package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.service.ProductService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {
    private final ProductService productService;

    @PostMapping("/productRegisterProApi")
    public ResponseEntity<String> registerProduct(@RequestBody @Validated ProductDTO productDTO, List<String> unitCodes) { //@Valid
        System.out.println("콘솔 로그 기록..");
        log.info("product insert test..");
        try {
            // 제품 등록 처리
            String productCode = productService.insertProduct(productDTO, unitCodes);
            return new ResponseEntity<>("Product registered successfully with code: " + productCode, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while registering product", e);
            return new ResponseEntity<>("Error occurred while registering product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
