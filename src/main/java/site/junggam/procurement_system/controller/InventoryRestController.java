package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.service.InventoryService;
import site.junggam.procurement_system.service.ProductService;
import site.junggam.procurement_system.service.ReleaseService;
import site.junggam.procurement_system.service.WarehousingService;

@Log4j2
@RestController
@RequestMapping("/inventorycontent")
@RequiredArgsConstructor
public class InventoryRestController {

    private final WarehousingService warehousingService;
    private final ReleaseService releaseService;
    private final InventoryService inventoryService;

    //입고관련
    @GetMapping(value = "/warehousingList", produces = "application/json")
    public ResponseEntity<PageResultDTO<WarehousingDTO, Warehousing>> getWarehousingList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<WarehousingDTO, Warehousing> warehousingDTOList = warehousingService.getAllWarehousingList(pageRequestDTO);
        return new ResponseEntity<>(warehousingDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/warehousing/{warehousingCode}")
    public ResponseEntity<WarehousingDTO> getWarehousing(@PathVariable("warehousingCode") String warehousingCode) {
        return new ResponseEntity<>(warehousingService.getWarehousing(warehousingCode), HttpStatus.OK);
    }

    @PostMapping(value = "/warehousing/history/{warehousingCode}")
    public ResponseEntity<String> saveWarehousingHistory(@RequestBody WarehousingHistoryDTO warehousingHistoryDTO) {
        log.info("응답받은 데이터"+warehousingHistoryDTO);
        String warehousingHistoryCode=warehousingService.saveWarehousingHistory(warehousingHistoryDTO);
        return new ResponseEntity<>(warehousingHistoryCode, HttpStatus.OK);
    }


    //출고관련
    @GetMapping(value = "/release/{releaseCode}")
    public ResponseEntity<ReleaseDTO> getReleaseCode(@PathVariable("releaseCode") String releaseCode) {
        return new ResponseEntity<>(releaseService.getReleaseRequest(releaseCode), HttpStatus.OK);
    }

    @PostMapping(value = "/release/{releaseCode}")
    public ResponseEntity<String> saveReleaseCode(@RequestBody ReleaseDTO releaseDTO) {
        log.info("응답받은 데이터는 "+releaseDTO);
        releaseService.saveReleaseRequest(releaseDTO);
        return new ResponseEntity<>("저장되었습니다", HttpStatus.OK);
    }
    @GetMapping(value = "/releaseList", produces = "application/json")
    public ResponseEntity<PageResultDTO<ReleaseDTO, Release>> getAllReleaseList(PageRequestDTO pageRequestDTO) {
        log.info("창고재고리스트 가져오기 레스트 컨트롤러 진입");
        PageResultDTO<ReleaseDTO, Release> releaseDTOList = releaseService.getReleaseList(pageRequestDTO);
        log.info("창고재고리스트 가져오기 완료");
        return new ResponseEntity<>(releaseDTOList, HttpStatus.OK);
    }


    //창고재고리스트
    @GetMapping(value = "/inventoryList", produces = "application/json")
    public ResponseEntity<PageResultDTO<InventoryDTO, Inventory>> getAllInventoryList(PageRequestDTO pageRequestDTO) {
        log.info("창고재고리스트 가져오기 레스트 컨트롤러 진입");
        PageResultDTO<InventoryDTO, Inventory> inventoryDTOList = inventoryService.getInventoryList(pageRequestDTO);
        log.info("창고재고리스트 가져오기 완료");
        return new ResponseEntity<>(inventoryDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/totallMaterialQuantity", produces = "application/json")
    public ResponseEntity<Integer> getTotallMaterialQuantity(){
        return new ResponseEntity<>(inventoryService.getTotallMaterialQuantity(), HttpStatus.OK);
    }
    @GetMapping(value = "/totallMaterialPrice", produces = "application/json")
    public ResponseEntity<Double> getTotallMaterialPrice(){
        return new ResponseEntity<>(inventoryService.getTotallMaterialPrice(), HttpStatus.OK);
    }
}
