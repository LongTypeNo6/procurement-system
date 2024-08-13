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
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.service.MaterialService;
import site.junggam.procurement_system.service.ProductService;
import site.junggam.procurement_system.service.UnitService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final UnitService unitService;
    private final MaterialService materialService;


    @RequestMapping("/addproducttest")
    public void addproducttest() {

    }

    @RequestMapping("/testList")
    public void testList() {

    }


    // 제품 조회 메소드
    @GetMapping("/getProduct/{productCode}")
    public String getProduct(@PathVariable String productCode, Model model) {
        log.info("제품 조회, 코드: " + productCode);

        Product product = productService.getProduct(productCode);
        if (product == null) {
            model.addAttribute("message", "제품을 찾을 수 없습니다.");
            return "error"; // error.html (에러 페이지)
        }

        ProductDTO productDTO = productService.entityToDTO(product);
        model.addAttribute("product", productDTO);

        return "product/getProduct"; // 제품 상세보기 페이지 (view.html)
    }


    // 제품 목록 메소드
    @RequestMapping("/getListProduct")
    public void getListProduct(Model model) {
        log.info("제품 목록 조회");

        List<Product> products = productService.getListProduct();
        List<ProductDTO> productDTOs = products.stream()
                .map(productService::entityToDTO) //this::entityToDTO
                .collect(Collectors.toList());

        //log.info("productDTOs : " + productDTOs);
        //List<Product> allProducts = productService.findAllProducts();

        model.addAttribute("products", productDTOs);

        //return "product/getListProduct"; // 제품 목록 페이지 (list.html)
    }


    // 유닛 목록 메소드
    @RequestMapping("getListUnit")
    public void getListUnit(Model model) {
        log.info("유닛 목록 조회..");

        List<Unit> units = unitService.getListUnit();
        List<UnitDTO> unitDTOS = units.stream()
                .map(unitService::entityToDTO)
                .collect(Collectors.toList());
        log.info("unitDTOs : ", unitDTOS);

        model.addAttribute("units", unitDTOS);

        //return "product/getListUnit"; //유닛 목록 페이지 (ulist.html)
    }


    // 자재 목록 메소드
    @RequestMapping("getListMaterial")
    public void getListMaterial(Model model) {
        log.info("자재 목록 조회..");

        List<Material> materials = materialService.getListMaterial();
        List<MaterialDTO> materialDTOS = materials.stream()
                .map(materialService::entityToDTO)
                .collect(Collectors.toList());
        log.info("materialDTOS : ", materialDTOS);

        model.addAttribute("materials", materialDTOS);

        //return "product/getListMaterial"; // 자재 목록 페이지 (mlist.html)
    }


    // 유닛 등록 페이지 폼
    @GetMapping("/unitRegister")
    public void unitRegister() {

    }

    // 유닛 등록 처리 메소드
    @PostMapping("/unitRegisterPro")
    public String unitRegisterPro(
            @RequestParam("unit_name") String unitName,
            @RequestParam("unit_stand") String unitStand,
            @RequestParam("unit_texture") String unitTexture,
            @RequestParam("unit_draw_file") MultipartFile unitDrawFile,
            @RequestParam("unit_etc_file") MultipartFile unitEtcFile,
            @RequestParam(value = "product_code", required = false) String productCode,
            @RequestParam(value = "material_code", required = false) String materialCode,
            RedirectAttributes redirectAttributes) {

        log.info("유닛등록 테스트..");

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
        String drawFilePath = saveFile(unitDrawFile);
        String etcFilePath = saveFile(unitEtcFile);

        // DTO 객체 생성 및 값 설정
        UnitDTO unitDTO = UnitDTO.builder()
                .unitName(unitName)
                .unitStand(unitStand)
                .unitTexture(unitTexture)
                .unitDrawFile(drawFilePath)
                .unitEtcFile(etcFilePath)
                .unitRegDate(now)
                .unitModDate(now)
                .productCode(productCode)  // Set Product Code
                .materialCode(materialCode)  // Set Material Code
                .build();

        // 유닛 등록 처리
        String unitCode = unitService.insertUnit(unitDTO);

        // 등록 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "유닛이 등록되었습니다. 코드: " + unitCode);

        // 유닛 목록 페이지로 리다이렉트
        return "redirect:/product/getListUnit";
    }

    // 자재 등록 페이지 폼
    @GetMapping("/materialRegister")
    public void materialRegister() {

    }

    // 자재 등록 처리 메소드
    @PostMapping("/materialRegisterPro")
    public String materialRegisterPro(
            @RequestParam("material_name") String materialName,
            @RequestParam("material_stand") String materialStand,
            @RequestParam("material_texture") String materialTexture,
            @RequestParam("material_draw_file") MultipartFile materialDrawFile,
            @RequestParam("material_etc_file") MultipartFile materialEtcFile,
            @RequestParam("material_safe_quantity") Integer materialSafeQuantity,
            RedirectAttributes redirectAttributes) {

        log.info("자재 등록 ..");

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
        String drawFilePath = saveFile(materialDrawFile);
        String etcFilePath = saveFile(materialEtcFile);

        // DTO 객체 생성 및 값 설정
        MaterialDTO materialDTO = MaterialDTO.builder()
                .materialName(materialName)
                .materialStand(materialStand)
                .materialTexture(materialTexture)
                .materialDrawFile(drawFilePath)
                .materialEtcFile(etcFilePath)
                .materialRegDate(now)
                .materialModDate(now)
                .materialSafeQuantity(materialSafeQuantity)
                .build();

        // 자재 등록 처리
        String materialCode = materialService.insertMaterial(materialDTO);

        // 등록 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "자재가 등록되었습니다. 코드: " + materialCode);

        // 자재 목록 페이지로 리다이렉트
        return "redirect:/product/getListMaterial";
    }


    // 제품 등록 페이지 폼
    @GetMapping("/productRegister")
    public void productRegister() {

    }

    // 제품 등록 처리 메소드
    @PostMapping("/productRegisterPro")
    public String productRegisterPro(
            @RequestParam("product_name") String productName,
            @RequestParam("product_price") Double productPrice,
            @RequestParam("product_stand") String productStand,
            @RequestParam("product_texture") String productTexture,
            @RequestParam("product_draw_file") MultipartFile productDrawFile,
            @RequestParam("product_etc_file") MultipartFile productEtcFile,
            RedirectAttributes redirectAttributes) {

        log.info("제품등록 테스트..");

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
        String drawFilePath = saveFile(productDrawFile);
        String etcFilePath = saveFile(productEtcFile);

        // DTO 객체 생성 및 값 설정
        ProductDTO productDTO = ProductDTO.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productStand(productStand)
                .productTexture(productTexture)
                .productDrawFile(drawFilePath)
                .productEtcFile(etcFilePath)
                .productRegDate(now)
                .productModDate(now)
                .build();

        // 제품 등록 처리
        String productCode = productService.insertProduct(productDTO);

        // 등록 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "제품이 등록되었습니다. 코드: " + productCode);

        // 제품 목록 페이지로 리다이렉트
        return "redirect:/product/getListProduct";
    }




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



}
