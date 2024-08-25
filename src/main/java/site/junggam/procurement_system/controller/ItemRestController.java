package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.PurchaserDTO;
import site.junggam.procurement_system.service.ItemService;

@Log4j2
@Controller
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
}
