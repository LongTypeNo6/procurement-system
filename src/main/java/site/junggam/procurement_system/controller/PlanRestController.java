package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.service.MaterialService;
import site.junggam.procurement_system.service.PlanService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanRestController {

    private final PlanService planService;

    @GetMapping(value = "/productListSearching", produces = "application/json")
    public ResponseEntity<List<ProductDTO>> getProductList(@RequestParam("keyword")String keyword) {
        log.info("받은 키워드는 "+keyword);
        List<ProductDTO> productDTOList = planService.getProductListSearching(keyword);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/unitListSearching", produces = "application/json")
    public ResponseEntity<List<UnitDTO>> getUnitList(@RequestParam("keyword")String keyword) {
        log.info("받은 키워드는 "+keyword);
        List<UnitDTO> unitDTOList = planService.getUnitListSearching(keyword);
        return new ResponseEntity<>(unitDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/materialListSearching", produces = "application/json")
    public ResponseEntity<List<MaterialDTO>> getMaterialList(@RequestParam("keyword")String keyword) {
        log.info("받은 키워드는 "+keyword);
        List<MaterialDTO> materialDTOList = planService.getMaterialListSearching(keyword);
        return new ResponseEntity<>(materialDTOList, HttpStatus.OK);
    }
}
