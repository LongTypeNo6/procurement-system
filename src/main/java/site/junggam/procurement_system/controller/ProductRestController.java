package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.PurchaserDTO;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.service.MaterialService;
import site.junggam.procurement_system.service.ProductService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/productcontent")
public class ProductRestController {

    private final ProductService productService;
    private final MaterialService materialService;

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

    @PostMapping("material/register")
    public ResponseEntity<String> materialRegister(@RequestBody MaterialDTO materialDTO) {
        log.info("응답받은 데이터"+materialDTO);
        materialService.insertMaterial(materialDTO);
        return new ResponseEntity<>("거래처등록완료", HttpStatus.OK);

    }


}
