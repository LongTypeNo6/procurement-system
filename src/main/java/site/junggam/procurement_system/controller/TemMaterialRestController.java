package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.service.TemMaterialService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/temMaterialcontent")
@RequiredArgsConstructor
public class TemMaterialRestController {

    private final TemMaterialService temMaterialService;

    @GetMapping(value = "/materialList", produces = "application/json")
    public ResponseEntity<List<TemMaterialDTO>> getTemMaterialList(@RequestParam("keyword")String keyword) {
        log.info("받은 키워드는 "+keyword);
        List<TemMaterialDTO> temMaterialDTOList = temMaterialService.getTemMaterialList(keyword);
        return new ResponseEntity<>(temMaterialDTOList, HttpStatus.OK);
    }
}
