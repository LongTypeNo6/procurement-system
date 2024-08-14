package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.InspectionPlan;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.service.InspectionPlanService;
import site.junggam.procurement_system.service.PurchaseOrderService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/purchaseordercontent")
@RequiredArgsConstructor
public class PurchaseOrderRestController {

    private final PurchaseOrderService purchaseOrderService;
    private final InspectionPlanService inspectionPlanService;

    @GetMapping(value = "/purchaseOrderList", produces = "application/json")
    public ResponseEntity<PageResultDTO<PurchaseOrderDTO, PurchaseOrder>> getAllPurchaseOrders(PageRequestDTO pageRequestDTO) {
        log.info("발주리스트 가져오기 레스트 컨트롤러 진입");
        PageResultDTO<PurchaseOrderDTO, PurchaseOrder> purchaseOrderDTOList = purchaseOrderService.getPurchaseOrderList(pageRequestDTO);
        log.info("발주리스트 가져오기 완료");
        return new ResponseEntity<>(purchaseOrderDTOList, HttpStatus.OK);
    }

    @GetMapping("/{purchaseOrderCode}")
    public ResponseEntity<PurchaseOrderDTO> purchaseOrderGet(@PathVariable("purchaseOrderCode") String purchaseOrderCode){
        log.info("발주상세정보 레스트 컨트롤러 진입");
        log.info("발주코드는 "+purchaseOrderCode);
        PurchaseOrderDTO purchaseOrderDTO=purchaseOrderService.getPurchaseOrder(purchaseOrderCode);
        log.info("발주상세보기 가져오기 완료");
        return new ResponseEntity<>(purchaseOrderDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/{purchaseOrderCode}")
    public ResponseEntity<String> purchaseOrderSave(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        log.info("발주수정 레스트컨트롤러 집입");
        log.info(purchaseOrderDTO);
        purchaseOrderService.savePurchaseOrder(purchaseOrderDTO);
        log.info("발주처리 완료!!!");
        return new ResponseEntity<>("발주처리되었습니다", HttpStatus.OK);
    }

    @GetMapping(value = "/inspectionPlanList", produces = "application/json")
    public ResponseEntity<PageResultDTO<InspectionPlanDTO, InspectionPlan>> getAllInspectionPlans(PageRequestDTO pageRequestDTO) {
        log.info("검수관리리스트 가져오기 레스트 컨트롤러 진입");
        PageResultDTO<InspectionPlanDTO,InspectionPlan> inspectionPlanDTOList = inspectionPlanService.getInspectionPlanList(pageRequestDTO);
        log.info("검수리스트 가져오기 완료");
        return new ResponseEntity<>(inspectionPlanDTOList, HttpStatus.OK);
    }

    @GetMapping("/inspectionPlan/{purchaseOrderCode}")
    public ResponseEntity<List<InspectionPlanDTO>> getInspectionPlan(@PathVariable("purchaseOrderCode") String purchaseOrderCode){
        log.info("검수계획 상세보기 레스트 컨트롤러 진입");
        log.info("발주코드는 "+purchaseOrderCode);
        List<InspectionPlanDTO> InspectionPlanDTOList = inspectionPlanService.getInspectionPlan(purchaseOrderCode);
        log.info(InspectionPlanDTOList);
        return new ResponseEntity<>(InspectionPlanDTOList, HttpStatus.OK);
    }


    @PostMapping("/inspectionPlan/{purchaseOrderCode}")
    public ResponseEntity<String> inspectionPlanSave(@RequestBody InspectionPlanDTO inspectionPlanDTO){
        log.info("검수계획등록 레스트컨트롤러 집입");
        log.info(inspectionPlanDTO);
        inspectionPlanService.saveInspectionPlan(inspectionPlanDTO);
        return new ResponseEntity<>("검수계획등록되었습니다", HttpStatus.OK);
    }

    @PatchMapping("/inspectionPlan/{inspectionPlanCode}")
    public ResponseEntity<String> inspectionPlanModify(@RequestBody InspectionPlanDTO inspectionPlanDTO){
        log.info("검수수정 레스트컨트롤러 집입");
        log.info(inspectionPlanDTO);
        inspectionPlanService.modifyInspectionPlan(inspectionPlanDTO);
        return new ResponseEntity<>("검수수정처리되었습니다", HttpStatus.OK);
    }

    @PostMapping("/inspectionPlan/result/{purchaseOrderCode}")
    public ResponseEntity<String> inspectionResultSave(@RequestBody InspectionPlanDTO inspectionPlanDTO){
        log.info("검수결과등록 레스트컨트롤러 집입");
        log.info(inspectionPlanDTO);
        inspectionPlanService.saveInspectionResult(inspectionPlanDTO);
        return new ResponseEntity<>("검수계획등록되었습니다", HttpStatus.OK);
    }

    @GetMapping(value = "/inspectionPlanScheduleList", produces = "application/json")
    public ResponseEntity<PageResultDTO<InspectionPlanDTO, InspectionPlan>> getAllInspectionPlanSchedules(PageRequestDTO pageRequestDTO) {
        log.info("검수일정리스트 가져오기 레스트 컨트롤러 진입");
        PageResultDTO<InspectionPlanDTO,InspectionPlan> inspectionPlanDTOList = inspectionPlanService.getInspectionPlaScheduleList(pageRequestDTO);
        log.info("검수일정리스트 가져오기 완료");
        return new ResponseEntity<>(inspectionPlanDTOList, HttpStatus.OK);
    }

}

