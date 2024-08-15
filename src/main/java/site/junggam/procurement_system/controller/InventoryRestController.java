package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.service.WarehousingService;

@Log4j2
@RestController
@RequestMapping("/inventorycontent")
@RequiredArgsConstructor
public class InventoryRestController {

    private final WarehousingService warehousingService;

    @GetMapping(value = "/warehousingList", produces = "application/json")
    public ResponseEntity<PageResultDTO<WarehousingDTO, Warehousing>> getWarehousingList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<WarehousingDTO, Warehousing> warehousingDTOList = warehousingService.getAllWarehousingList(pageRequestDTO);
        return new ResponseEntity<>(warehousingDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/warehousing/{warehousingCode}")
    public ResponseEntity<WarehousingDTO> getWarehousing(@PathVariable("warehousingCode") String warehousingCode) {
        return new ResponseEntity<>(warehousingService.getWarehousing(warehousingCode), HttpStatus.OK);
    }
}
