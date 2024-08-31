package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.service.ItemService;

@Log4j2
@RestController
@RequestMapping("/itemcontent")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemService itemService;

    @PostMapping(value = "/material")
    public ResponseEntity<String> materialSave(@RequestBody MaterialDTO materialDTO){
        log.info("들어온 정보"+materialDTO);
        String materialCode=itemService.materialResister(materialDTO);
        return new ResponseEntity<>(materialCode, HttpStatus.OK);
    }

    @GetMapping("/material/{materialCode}")
    public ResponseEntity<MaterialDTO> materialGet(@PathVariable("materialCode") String materialCode){
        MaterialDTO materialDTO=itemService.materialGet(materialCode);
        return new ResponseEntity<>(materialDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/materialList", produces = "application/json")
    public ResponseEntity<PageResultDTO<MaterialDTO, Material>> materialList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<MaterialDTO, Material> materialDTOList = itemService.materialList(pageRequestDTO);
        return new ResponseEntity<>(materialDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "/unit")
    public ResponseEntity<String> unitSave(@RequestBody UnitDTO unitDTO){
        log.info("들어온 정보"+unitDTO);
        String unitCode=itemService.unitResister(unitDTO);
        return new ResponseEntity<>(unitCode, HttpStatus.OK);
    }

    @GetMapping("/unit/{unitCode}")
    public ResponseEntity<UnitDTO> unitGet(@PathVariable("unitCode") String unitCode){
        UnitDTO unitDTO=itemService.unitGet(unitCode);
        return new ResponseEntity<>(unitDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/unitList", produces = "application/json")
    public ResponseEntity<PageResultDTO<UnitDTO, Unit>> unitList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<UnitDTO, Unit> unitDTOList = itemService.unitList(pageRequestDTO);
        return new ResponseEntity<>(unitDTOList, HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<String> productSave(@RequestBody ProductDTO productDTO){
        log.info("들어온 정보"+productDTO);
        String productCode=itemService.productResister(productDTO);
        return new ResponseEntity<>(productCode, HttpStatus.OK);
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<ProductDTO> productGet(@PathVariable("productCode") String productCode){
        ProductDTO productDTO=itemService.productGet(productCode);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/productList", produces = "application/json")
    public ResponseEntity<PageResultDTO<ProductDTO, Product>> productList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<ProductDTO, Product> productDTOList = itemService.productList(pageRequestDTO);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}
