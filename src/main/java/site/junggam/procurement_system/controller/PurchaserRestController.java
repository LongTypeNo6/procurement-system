package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.service.PurchaserService;

@Log4j2
@RestController
@RequestMapping("/purchasercontent")
@RequiredArgsConstructor
public class PurchaserRestController {

    private final PurchaserService purchaserService;

    @GetMapping("/{purchaserCode}")
    public ResponseEntity<PurchaserDTO> purchaerGet(@PathVariable("purchaserCode") String purchaserCode){
        log.info("회사정보레스트컨트롤러진입");
        log.info("사업자등록번호: " + purchaserCode);
        PurchaserDTO purchaserDTO=purchaserService.getPurchaser(purchaserCode);
        return new ResponseEntity<>(purchaserDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> purchaserSave(@RequestBody PurchaserDTO purchaserDTO) {
        log.info("응답받은 데이터"+purchaserDTO);
        purchaserService.savePurchaser(purchaserDTO);
        return new ResponseEntity<>("거래처등록완료", HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<PageResultDTO<PurchaserDTO, Purchaser>> getPurchaserList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<PurchaserDTO, Purchaser> purchaserDTOList = purchaserService.getPurchaserList(pageRequestDTO);
        return new ResponseEntity<>(purchaserDTOList, HttpStatus.OK);
    }
}
