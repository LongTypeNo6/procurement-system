package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.service.ItemService;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    //자재리스트
    @RequestMapping("/materialList")
    public void materialList() {
    }

    //자재등록하기
    @RequestMapping("/materialRegister")
    public void materialRegister(){
    }

    //자재상세보기
    @GetMapping("/materialGet")
    public void materialGet(@RequestParam("materialCode") String materialCode, Model model){
        model.addAttribute("materialCode",materialCode);
    }

    //유닛리스트
    @RequestMapping("/unitList")
    public void unitList() {
    }

    //유닛등록하기
    @RequestMapping("/unitRegister")
    public void unitRegister(){
    }

    //유닛상세보기
    @GetMapping("/unitGet")
    public void unitGet(@RequestParam("unitCode") String unitCode, Model model){
        model.addAttribute("unitCode",unitCode);
    }

    //제품리스트
    @RequestMapping("/productList")
    public void productList() {
    }

    //제품등록하기
    @RequestMapping("/productRegister")
    public void productRegister(){
    }

    //제품상세보기
    @GetMapping("/productGet")
    public void productGet(@RequestParam("productCode") String productCode, Model model){
        model.addAttribute("productCode",productCode);
    }

}
