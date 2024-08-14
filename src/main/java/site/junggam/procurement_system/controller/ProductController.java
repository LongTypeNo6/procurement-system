package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.ProductDTO;
import site.junggam.procurement_system.dto.ProductUnitDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.service.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductUnitService productUnitService;
    private final UnitService unitService;
    private final UnitMaterialService unitMaterialService;
    private final MaterialService materialService;


    // 테스트 부분..
    @RequestMapping("/addproducttest")
    public void addproducttest() {

    }

    @RequestMapping("/testList")
    public void testList() {

    }



    ////////// (제품 컨트롤러 부분) /////////////////////////////////////////////////////////////////////

    // 제품 등록 페이지 폼
    @GetMapping("/productRegister")
    public void productRegister(Model model) {
        model.addAttribute("product", new ProductDTO());
    }

    // 제품 등록 처리 메소드
    @PostMapping("/productRegisterPro")
    public String productRegisterPro(
            @ModelAttribute ProductDTO productDTO,
            RedirectAttributes redirectAttributes) {

        log.info("제품 등록 ..");

        productService.insertProduct(productDTO);

        // 제품 목록 페이지로 리다이렉트
        return "redirect:/product/getListProduct";
    }

    // 제품 수정 페이지 폼
    @GetMapping("/productModify/{productCode}")
    public void productModify(@PathVariable String productCode, Model model) {
        Optional<ProductDTO> product = productService.getProduct(productCode);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            //return "redirect:/product/productModify"; // View name (JSP or Thymeleaf template)
        } else {
            //return "error/404"; // Handle not found
        }
    }

    // 제품 수정 처리 메서드
    @PostMapping("/productModifyPro")
    public String productModifyPro(@ModelAttribute ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return "redirect:/product/getListProduct";
    }

    // 제품 삭제 처리 메서드
    @GetMapping("productDeletePro")
    public void productDeletePro(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
    }

    // 제품 조회 메소드
    @GetMapping("/getProduct/{productCode}")
    public void getProduct(@PathVariable String productCode, Model model) {
        log.info("제품 조회, 코드: " + productCode);

        Optional<ProductDTO> product = productService.getProduct(productCode);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            //return "redirect:/product/getProduct"; // View name (JSP or Thymeleaf template)
        } else {
            //return "error/404"; // Handle not found
        }

        //return "product/getProduct"; // 제품 상세보기 페이지 (view.html)
    }


    // 제품 목록 메소드
    @RequestMapping("/getListProduct")
    public void getListProduct(Model model) {
        log.info("제품 목록 조회");

        List<ProductDTO> products = productService.getListProduct();
        model.addAttribute("products", products);

        //return "product/getListProduct"; // 제품 목록 페이지 (list.html)
    }

    // 제품 검색 메서드
    @RequestMapping("/searchProduct")
    public String searchProduct(@RequestParam String keyword, Model model) {
        List<ProductDTO> products = productService.searchProduct(keyword);
        model.addAttribute("products", products);
        return "redirect:/product/getListProduct";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////




    ////////// ((제품+유닛) 컨트롤러 부분) ///////////////////////////////////////////////////////////////
    // (제품+유닛) 등록 메서드
    @GetMapping("/productUnitRegister")
    public void productUnitRegister(Model model) {
        model.addAttribute("productUnit", new ProductUnitDTO());
    }
    // (제품+유닛) 등록 처리 메서드
    @PostMapping("/productUnitRegisterPro")
    public String productUnitRegisterPro(@ModelAttribute ProductUnitDTO productUnitDTO, RedirectAttributes redirectAttributes) {
        log.info("제품 등록 ..");

        productUnitService.insertProductUnit(productUnitDTO);

        // 제품 목록 페이지로 리다이렉트
        return "redirect:/product/getListProduct";
    }

    // (제품+유닛) 수정 메서드


    // (제품+유닛) 삭제 메서드


    // (제품+유닛) 조회 메서드


    // (제품+유닛) 목록 조회 메서드


    // (제품+유닛) 검색 메서드

    ///////////////////////////////////////////////////////////////////////////////////////////////////




    ////////// (유닛 컨트롤러 부분) /////////////////////////////////////////////////////////////////////
    // 유닛 등록 페이지 폼
    @GetMapping("/unitRegister")
    public void unitRegister(Model model) {
        model.addAttribute("unit", new UnitDTO());
    }

    // 유닛 등록 처리 메소드
    @PostMapping("/unitRegisterPro")
    public String unitRegisterPro(
            @ModelAttribute UnitDTO unitDTO,
            RedirectAttributes redirectAttributes) {

        log.info("유닛 등록 ..");

        unitService.insertUnit(unitDTO);

        // 유닛 목록 페이지로 리다이렉트
        return "redirect:/product/getListUnit";
    }

    // 유닛 수정 페이지 폼
    @GetMapping("/unitModify")
    public void unitModify(@PathVariable String unitCode, Model model) {
        Optional<UnitDTO> unit = unitService.getUnit(unitCode);
        if (unit.isPresent()) {
            model.addAttribute("unit", unit.get());
            //return "redirect:/product/unitModify"; // View name (JSP or Thymeleaf template)
        } else {
            //return "error/404"; // Handle not found
        }
    }

    // 유닛 수정 처리 메소드
    @PostMapping("/unitModifyPro")
    public String unitModifyPro(@ModelAttribute UnitDTO unitDTO) {
        unitService.updateUnit(unitDTO);
        return "redirect:/product/getListUnit";
    }

    // 유닛 삭제 처리 메서드
    @GetMapping("/unitDeletePro")
    public String unitDeletePro(@PathVariable String unitCode) {
        unitService.deleteUnit(unitCode);
        return "redirect:/product/getListUnit";
    }


    // 유닛 조회 메소드
    @GetMapping("/getUnit/{unitCode}")
    public void getUnit(@PathVariable String unitCode, Model model) {
        log.info("유닛 조회, 코드: " + unitCode);

        Optional<UnitDTO> unit = unitService.getUnit(unitCode);
        if (unit.isPresent()) {
            model.addAttribute("unit", unit.get());
            //return "redirect:/product/getUnit"; // View name (JSP or Thymeleaf template)
        } else {
            //return "error/404"; // Handle not found
        }
    }

    // 유닛 목록 메소드
    @RequestMapping("/getListUnit")
    public void getListUnit(Model model) {
        log.info("유닛 목록 조회..");

        List<UnitDTO> units = unitService.getListUnit();
        model.addAttribute("units", units);

        //return "product/getListUnit"; //유닛 목록 페이지 (ulist.html)
    }

    @RequestMapping("/searchUnit")
    public String searchUnit(@RequestParam String keyword, Model model) {
        List<UnitDTO> units = unitService.searchUnit(keyword);
        model.addAttribute("units", units);
        return "redirect:/product/getListUnit";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////




    ////////// ((유닛+자재) 컨트롤러 부분) ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////




    ////////// (자재 컨트롤러 부분) /////////////////////////////////////////////////////////////////////
    // 자재 등록 페이지 폼
    @GetMapping("/materialRegister")
    public void materialRegister(Model model) {
        model.addAttribute("materialDTO", new MaterialDTO());
        //return "redirect:/product/materialRegister";
    }

    // 자재 등록 처리 메소드
    @PostMapping("/materialRegisterPro")
    public String materialRegisterPro(
            @ModelAttribute MaterialDTO materialDTO,
            RedirectAttributes redirectAttributes) {

        log.info("자재 등록 ..");

        materialService.insertMaterial(materialDTO);

        // 자재 목록 페이지로 리다이렉트
        return "redirect:/product/getListMaterial";
    }

    // 자재 수정 폼 메서드
    @GetMapping("/materialModify/{materialCode}")
    public void materialModify(@PathVariable String materialCode, Model model) {
        MaterialDTO materialDTO = materialService.getMaterial(materialCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid material code"));
        model.addAttribute("materialDTO", materialDTO);
    }

    // 자재 수정 처리 메서드
    @PostMapping("/materialModifyPro")
    public String materialModifyPro(@ModelAttribute MaterialDTO materialDTO, RedirectAttributes redirectAttributes) {
        materialService.updateMaterial(materialDTO);
        redirectAttributes.addFlashAttribute("message", "Material updated successfully!");
        return "redirect:/product/getListMaterial";
    }

    // 자재 삭제 처리 메서드
    @GetMapping("/materialDeletePro")
    public String materialDeletePro(@PathVariable String materialCode, RedirectAttributes redirectAttributes) {
        materialService.deleteMaterial(materialCode);
        redirectAttributes.addFlashAttribute("message", "Material deleted successfully!");
        return "redirect:/product/getListMaterial";
    }

    // 자재 조회 메서드
    @GetMapping("/getMaterial/{materialCode}")
    public void getMaterial(@PathVariable String materialCode, Model model) {
        log.info("자재 조회, 코드: " + materialCode);

        Optional<MaterialDTO> material = materialService.getMaterial(materialCode);
        if (material.isPresent()) {
            model.addAttribute("material", material.get());
            //return "redirect:/product/getMaterial"; // View name (JSP or Thymeleaf template)
        } else {
            //return "error/404"; // Handle not found
        }
    }

    // 자재 목록 메소드
    @RequestMapping("getListMaterial")
    public void getListMaterial(Model model) {
        log.info("자재 목록 조회..");

        List<MaterialDTO> materials = materialService.getListMaterial();
        model.addAttribute("materials", materials);

        //return "product/getListMaterial"; // 자재 목록 페이지 (mlist.html)
    }

    // 자재 검색 메소드
    @RequestMapping("/searchMaterial")
    public String searchMaterial(@RequestParam String keyword, Model model) {
        List<MaterialDTO> materials = materialService.searchMaterial(keyword);
        model.addAttribute("materials", materials);
        return "redirect:/product/getListUnit";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////








    ////////// (공통 컨트롤러 부분) /////////////////////////////////////////////////////////////////////
    // 파일 저장 메소드
    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        // 파일 저장 경로 설정 (여기서는 예를 들기 위해 /tmp/ 디렉토리에 저장)
        //String filePath = "/tmp/" + file.getOriginalFilename();
        String filePath = "C:/upload/" + file.getOriginalFilename();

        try {
            // 파일을 지정한 경로에 저장
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생", e);
            return null;
        }

        return filePath;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////


}
