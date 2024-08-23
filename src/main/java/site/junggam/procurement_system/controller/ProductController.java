package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.repository.MaterialRepository;
import site.junggam.procurement_system.repository.UnitRepository;
import site.junggam.procurement_system.service.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    private final UnitRepository unitRepository;
    private final MaterialRepository materialRepository;


    // 제품 등록 페이지 테스트 메서드..
    @RequestMapping("/addproducttest")
    public void addproducttest() {

    }



    ////////// (제품 컨트롤러 부분) /////////////////////////////////////////////////////////////////////
    // 제품 등록 페이지 폼
    @GetMapping("/productRegister")
    public void productRegister(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("units", unitService.getListUnit());
    }

    // 제품 등록 처리 메소드
    @PostMapping("/productRegisterPro")
    public String productRegisterPro(@ModelAttribute("product") Product product,
                                     @RequestParam("unitCodes") List<String> unitCodes,
            @RequestParam("product_name") String productName,
            @RequestParam("product_price") Double productPrice,
            @RequestParam("product_stand") String productStand,
            @RequestParam("product_texture") String productTexture,
            @RequestParam("product_draw_file") MultipartFile productDrawFile,
            @RequestParam("product_etc_file") MultipartFile productEtcFile,
            RedirectAttributes redirectAttributes) {

        log.info("제품 등록 ..");

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
        productService.insertProduct(productDTO, unitCodes);

        // 등록 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "제품이 등록되었습니다. 코드: " + productDTO.getProductCode());

        // 제품 목록 페이지로 리다이렉트
        return "redirect:/product/getListProduct";
    }

    // 제품 수정 페이지 폼1
    @GetMapping("/productModify/{productCode}")
    public String productModify(@PathVariable("productCode") String productCode, Model model) {
        model.addAttribute("product", productService.getProduct(productCode));
        model.addAttribute("units", unitRepository.findAll());
        //return "redirect:/product/productModify/{productCode}";
        return "redirect:/product/productEdit?productCode=" + productCode;
    }

    // 제품 수정 폼2(redirect)
    @GetMapping("/productEdit")
    public void productEdit(@RequestParam("productCode") String productCode, Model model) {
        log.info("제품수정폼 : " + productCode);

        // Product 객체를 서비스에서 조회하여 모델에 추가
        Optional<ProductDTO> productDTO = productService.getProduct(productCode);

        // 기본값을 설정할 ProductDTO 객체를 선언
        //ProductDTO productWithUnits = null;

        if(productDTO.isPresent()) {
            ProductDTO product = productDTO.get();

            // 날짜를 LocalDate로 변환하여 문자열로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String regDateFormatted = product.getProductRegDate().toLocalDate().format(formatter);
            model.addAttribute("productRegDate", regDateFormatted);
            String modDateFormatted = product.getProductModDate().toLocalDate().format(formatter);
            model.addAttribute("productModDate", modDateFormatted);

            // ProductUnitDTO를 기반으로 UnitDTO를 설정
            List<UnitDTO> unitDTOList = product.getProductUnits().stream()
                    .map(pud -> unitService.getUnit(pud.getUnitCode()).orElse(null))
                    .filter(unit -> unit != null)
                    .collect(Collectors.toList());

            model.addAttribute("units", unitDTOList);
            log.info("units : "+ unitDTOList);

            // 새로운 ProductDTO를 생성 (UnitDTO 리스트로 설정)
            ProductDTO productWithUnits = ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productStand(product.getProductStand())
                    .productTexture(product.getProductTexture())
                    .productDrawFile(product.getProductDrawFile())
                    .productEtcFile(product.getProductEtcFile())
                    .productRegDate(product.getProductRegDate())
                    .productModDate(product.getProductModDate())
                    .productUnits(product.getProductUnits())
                    .build();
        }
        //model.addAttribute("product", productWithUnits);
        //log.info("productDTO : "+productWithUnits);

        model.addAttribute("product", productDTO);
        log.info("productDTO : "+productDTO);

        //return "productEdit"; // 수정폼 이름
    }

    // 제품 수정 처리 메서드
    @PostMapping("/productModifyPro")
    public String productModifyPro(@RequestParam("product_code") String productCode,
                                   //@ModelAttribute("productDTO") ProductDTO productDTO,
                                   @ModelAttribute("product") Product product,
                                   @RequestParam("unitCodes") List<String> unitCodes,
                                   @RequestParam("product_name") String productName,
                                   @RequestParam("product_price") Double productPrice,
                                   @RequestParam("product_stand") String productStand,
                                   @RequestParam("product_texture") String productTexture,
                                   @RequestParam("product_draw_file") MultipartFile productDrawFile,
                                   @RequestParam("product_etc_file") MultipartFile productEtcFile,
                                   RedirectAttributes redirectAttributes) {

        log.info("제품 수정 : "+productCode);

        // 제품의 현재 상태를 데이터베이스에서 조회
        Optional<ProductDTO> existingProduct = productService.getProduct(productCode);

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
//        String drawFilePath = saveFile(productDrawFile);
//        String etcFilePath = saveFile(productEtcFile);
        String drawFilePath = existingProduct.get().getProductDrawFile();
        String etcFilePath = existingProduct.get().getProductEtcFile();

        // 파일 저장 처리
        if (productDrawFile != null && !productDrawFile.isEmpty()) {
            drawFilePath = saveFile(productDrawFile); // 새 파일 저장
        }
        if (productEtcFile != null && !productEtcFile.isEmpty()) {
            etcFilePath = saveFile(productEtcFile); // 새 파일 저장
        }

        // DTO 객체 생성 및 값 설정
        ProductDTO productDTO = ProductDTO.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productStand(productStand)
                .productTexture(productTexture)
                .productDrawFile(drawFilePath)
                .productEtcFile(etcFilePath)
                .productRegDate(existingProduct.get().getProductRegDate())
                .productModDate(now)
                .build();

        // 제품 수정 처리
        productService.updateProduct(productCode, productDTO, unitCodes);

        // 수정 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "제품이 수정되었습니다. 코드: " + productDTO.getProductCode());

        return "redirect:/product/getListProduct";
    }

    // 제품 삭제 처리 메서드
    @GetMapping("productDeletePro")
    public void productDeletePro(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
    }

    // 제품 조회 메소드
    @GetMapping("/getProduct/{productCode}")
    public String getProduct(@PathVariable("productCode") String productCode, Model model) {
        log.info("제품 조회 : " + productCode);

        model.addAttribute("product", productService.getProduct(productCode));
        //return "/product/productDetail"; // 제품 상세보기 페이지 (view.html)
        //return "redirect:/product/productDetail";
        return "redirect:/product/productDetail?productCode=" + productCode;
    }

    // 제품 조회 폼
    @GetMapping("/productDetail")
    public void productDetail(@RequestParam("productCode") String productCode, Model model) {
        log.info("제품상세보기 : " + productCode);

        // Product 객체를 서비스에서 조회하여 모델에 추가
        Optional<ProductDTO> productDTO = productService.getProduct(productCode);

        // 기본값을 설정할 ProductDTO 객체를 선언
        //ProductDTO productWithUnits = null;

        if(productDTO.isPresent()) {
            ProductDTO product = productDTO.get();

            // 날짜를 LocalDate로 변환하여 문자열로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String regDateFormatted = product.getProductRegDate().toLocalDate().format(formatter);
            model.addAttribute("productRegDate", regDateFormatted);
            String modDateFormatted = product.getProductModDate().toLocalDate().format(formatter);
            model.addAttribute("productModDate", modDateFormatted);

            // ProductUnitDTO를 기반으로 UnitDTO를 설정
            List<UnitDTO> unitDTOList = product.getProductUnits().stream()
                    .map(pud -> unitService.getUnit(pud.getUnitCode()).orElse(null))
                    .filter(unit -> unit != null)
                    .collect(Collectors.toList());

            model.addAttribute("units", unitDTOList);
            log.info("units : "+ unitDTOList);

            // 새로운 ProductDTO를 생성 (UnitDTO 리스트로 설정)
            ProductDTO productWithUnits = ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productStand(product.getProductStand())
                    .productTexture(product.getProductTexture())
                    .productDrawFile(product.getProductDrawFile())
                    .productEtcFile(product.getProductEtcFile())
                    .productRegDate(product.getProductRegDate())
                    .productModDate(product.getProductModDate())
                    .productUnits(product.getProductUnits())
                    .build();
        }
        //model.addAttribute("product", productWithUnits);
        //log.info("productDTO : "+productWithUnits);

        model.addAttribute("product", productDTO);
        log.info("productDTO : "+productDTO);

        //return "productDetail"; // 뷰 이름
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
    //필요시 별도 메소드 구현..
    // (제품+유닛) 등록 메서드
    // (제품+유닛) 등록 처리 메서드
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
        //model.addAttribute("unit", new UnitDTO());
        model.addAttribute("unit", new Unit());
        model.addAttribute("materials", materialService.getListMaterial());
    }

    // 유닛 등록 처리 메소드
    @PostMapping("/unitRegisterPro")
    public String unitRegisterPro(@ModelAttribute("unit") Unit unit,
                                  @RequestParam("unit_name") String unitName,
                                  @RequestParam("unit_stand") String unitStand,
                                  @RequestParam("unit_texture") String unitTexture,
                                  @RequestParam("unit_draw_file") MultipartFile unitDrawFile,
                                  @RequestParam("unit_etc_file") MultipartFile unitEtcFile,
                                  @RequestParam("materialCodes") List<String> materialCodes,
            RedirectAttributes redirectAttributes) {

        log.info("유닛 등록 ..");

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
                .build();

        // 유닛 등록 처리
        unitService.insertUnit(unitDTO, materialCodes);

        // 등록 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "유닛이 등록되었습니다. 코드: " + unitDTO.getUnitCode());

        // 유닛 목록 페이지로 리다이렉트
        return "redirect:/product/getListUnit";
    }

    // 유닛 수정 페이지 폼1
    @GetMapping("/unitModify/{unitCode}")
    public String unitModify(@PathVariable("unitCode") String unitCode, Model model) {
        return "redirect:/product/unitEdit?unitCode=" + unitCode;
    }

    // 유닛 수정 페이지 폼2
    @GetMapping("/unitEdit")
    public void unitEdit(@RequestParam("unitCode") String unitCode, Model model) {
        log.info("유닛수정폼 : " + unitCode);

        // Unit 객체를 서비스에서 조회하여 모델에 추가
        Optional<UnitDTO> unitDTO = unitService.getUnit(unitCode);

        if(unitDTO.isPresent()) {
            UnitDTO unitDTO2 = unitDTO.get();

            // 날짜를 LocalDate로 변환하여 문자열로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String regDateFormatted = unitDTO2.getUnitRegDate().toLocalDate().format(formatter);
            model.addAttribute("unitRegDate", regDateFormatted);
            String modDateFormatted = unitDTO2.getUnitModDate().toLocalDate().format(formatter);
            model.addAttribute("unitModDate", modDateFormatted);

            // UnitMaterialDTO를 기반으로 MaterialDTO를 설정
            Set<MaterialDTO> materialDTOList = unitDTO2.getUnitMaterials().stream()
                    .map(pud -> materialService.getMaterial(pud.getMaterialCode()).orElse(null))
                    .filter(material -> material != null)
                    .collect(Collectors.toSet());

            model.addAttribute("materials", materialDTOList);
            log.info("materials : "+ materialDTOList);
        }

        model.addAttribute("unit", unitDTO);
        log.info("unit : "+unitDTO);

        //return "unitEdit"; // 수정폼 이름
    }

    // 유닛 수정 처리 메소드
    @PostMapping("/unitModifyPro")
    public String unitModifyPro(@RequestParam("unit_code") String unitCode,
                                @ModelAttribute("unit") Unit unit,
                                //@ModelAttribute UnitDTO unitDTO,
                                @RequestParam("unit_name") String unitName,
                                @RequestParam("unit_stand") String unitStand,
                                @RequestParam("unit_texture") String unitTexture,
                                @RequestParam("unit_draw_file") MultipartFile unitDrawFile,
                                @RequestParam("unit_etc_file") MultipartFile unitEtcFile,
                                @RequestParam("materialCodes") List<String> materialCodes,
                                RedirectAttributes redirectAttributes) {

        log.info("유닛 수정 : "+unitCode);

        // 유닛의 현재 상태를 데이터베이스에서 조회
        Optional<UnitDTO> existingUnit = unitService.getUnit(unitCode);

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
//        String drawFilePath = saveFile(unitDrawFile);
//        String etcFilePath = saveFile(unitEtcFile);
        String drawFilePath = existingUnit.get().getUnitDrawFile();
        String etcFilePath = existingUnit.get().getUnitEtcFile();

        // 파일 저장 처리
        if (unitDrawFile != null && !unitDrawFile.isEmpty()) {
            drawFilePath = saveFile(unitDrawFile); // 새 파일 저장
        }
        if (unitEtcFile != null && !unitEtcFile.isEmpty()) {
            etcFilePath = saveFile(unitEtcFile); // 새 파일 저장
        }

        // DTO 객체 생성 및 값 설정
        UnitDTO unitDTO = UnitDTO.builder()
                //.unitCode(unitCode)
                .unitName(unitName)
                .unitStand(unitStand)
                .unitTexture(unitTexture)
                .unitDrawFile(drawFilePath)
                .unitEtcFile(etcFilePath)
                .unitRegDate(existingUnit.get().getUnitRegDate())
                .unitModDate(now)
                .build();

        // 유닛 수정 처리
        unitService.updateUnit(unitCode, unitDTO, materialCodes);

        // 수정 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "유닛이 수정되었습니다. 코드: " + unitDTO.getUnitCode());

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
    public String getUnit(@PathVariable("unitCode") String unitCode, Model model) {
        log.info("유닛 조회 : " + unitCode);

        model.addAttribute("unit", unitService.getUnit(unitCode));
        //return "/product/unitDetail"; // 유닛 상세보기 페이지 (view.html)
        //return "redirect:/product/unitDetail";
        return "redirect:/product/unitDetail?unitCode=" + unitCode;
    }

    // 유닛 조회 폼
    @GetMapping("/unitDetail")
    public void unitDetail(@RequestParam("unitCode") String unitCode, Model model) {
        log.info("유닛상세보기 : " + unitCode);

        // Unit 객체를 서비스에서 조회하여 모델에 추가
        Optional<UnitDTO> unitDTO = unitService.getUnit(unitCode);

        if(unitDTO.isPresent()) {
            UnitDTO unitDTO2 = unitDTO.get();

            // 날짜를 LocalDate로 변환하여 문자열로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String regDateFormatted = unitDTO2.getUnitRegDate().toLocalDate().format(formatter);
            model.addAttribute("unitRegDate", regDateFormatted);
            String modDateFormatted = unitDTO2.getUnitModDate().toLocalDate().format(formatter);
            model.addAttribute("unitModDate", modDateFormatted);

            // UnitMaterialDTO를 기반으로 MaterialDTO를 설정
            Set<MaterialDTO> materialDTOList = unitDTO2.getUnitMaterials().stream()
                    .map(pud -> materialService.getMaterial(pud.getMaterialCode()).orElse(null))
                    .filter(material -> material != null)
                    .collect(Collectors.toSet());

            model.addAttribute("materials", materialDTOList);
            log.info("materials : "+ materialDTOList);

            // 새로운 UnitDTO를 생성 (MeterialDTO 리스트로 설정)
        }

        model.addAttribute("unit", unitDTO);
        log.info("unit : "+unitDTO);

        //return "unitDetail"; // 뷰 이름
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
    //필요시 별도 메소드 구현..
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
            @RequestParam("material_name") String materialName,
            @RequestParam("material_stand") String materialStand,
            @RequestParam("material_texture") String materialTexture,
            @RequestParam("material_draw_file") MultipartFile materialDrawFile,
            @RequestParam("material_etc_file") MultipartFile materialEtcFile,
            @RequestParam("material_safe_quantity") Integer materialSafeQuantity,
            //@ModelAttribute MaterialDTO materialDTO,
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

    // 자재 수정 폼1 메서드
    @GetMapping("/materialModify/{materialCode}")
    public String materialModify(@PathVariable("materialCode") String materialCode, Model model) {
        return "redirect:/product/materialEdit?materialCode=" + materialCode;
    }

    // 자재 수정 폼2 메서드
    @GetMapping("/materialEdit")
    public void materialEdit(@RequestParam("materialCode") String materialCode, Model model) {
        log.info("자재수정폼 : " + materialCode);

        // material 객체를 서비스에서 조회하여 모델에 추가
        Optional<MaterialDTO> materialDTO = materialService.getMaterial(materialCode);

        if(materialDTO.isPresent()) {
            model.addAttribute("material", materialDTO);
            log.info("material : " + materialDTO);
        }

        //return "materialEdit"; // 수정폼 이름
    }

    // 자재 수정 처리 메서드
    @PostMapping("/materialModifyPro")
    public String materialModifyPro(@RequestParam("material_code") String materialCode,
                                    @RequestParam("material_name") String materialName,
                                    @RequestParam("material_stand") String materialStand,
                                    @RequestParam("material_texture") String materialTexture,
                                    @RequestParam("material_draw_file") MultipartFile materialDrawFile,
                                    @RequestParam("material_etc_file") MultipartFile materialEtcFile,
                                    @RequestParam("material_safe_quantity") Integer materialSafeQuantity,
                                    //@ModelAttribute("materialDTO") MaterialDTO mtDTO,
                                    //@ModelAttribute("material") Material material,
                                    RedirectAttributes redirectAttributes) {

        log.info("자재 수정 : " + materialCode);

        // 자재의 현재 상태를 데이터베이스에서 조회
        Optional<MaterialDTO> existingMaterial = materialService.getMaterial(materialCode);

        // 현재 날짜와 시간을 등록일 및 수정일로 설정
        LocalDateTime now = LocalDateTime.now();

        // 파일 저장 처리
//        String drawFilePath = saveFile(materialDrawFile);
//        String etcFilePath = saveFile(materialEtcFile);
        String drawFilePath = existingMaterial.get().getMaterialDrawFile();
        String etcFilePath = existingMaterial.get().getMaterialEtcFile();

        // 파일 저장 처리
        if (materialDrawFile != null && !materialDrawFile.isEmpty()) {
            drawFilePath = saveFile(materialDrawFile); // 새 파일 저장
        }
        if (materialEtcFile != null && !materialEtcFile.isEmpty()) {
            etcFilePath = saveFile(materialEtcFile); // 새 파일 저장
        }

        // DTO 객체 생성 및 값 설정
        MaterialDTO materialDTO = MaterialDTO.builder()
                .materialCode(materialCode)
                .materialName(materialName)
                .materialStand(materialStand)
                .materialTexture(materialTexture)
                .materialDrawFile(drawFilePath)
                .materialEtcFile(etcFilePath)
                .materialRegDate(existingMaterial.get().getMaterialRegDate())
                .materialModDate(now)
                .materialSafeQuantity(materialSafeQuantity)
                .build();

        // 자재 수정 처리
        materialService.updateMaterial(materialDTO);

        // 수정 성공 메시지 설정
        redirectAttributes.addFlashAttribute("message", "자재가 수정되었습니다. 코드: " + materialCode);

        // 자재 목록 페이지로 리다이렉트
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
    public String getMaterial(@PathVariable("materialCode") String materialCode, Model model) {
        log.info("자재 조회 : " + materialCode);

        return "redirect:/product/materialDetail?materialCode=" + materialCode;
    }

    // 자재 조회 폼
    @GetMapping("/materialDetail")
    public void materialDetail(@RequestParam("materialCode") String materialCode, Model model) {
        log.info("자재상세보기 : " + materialCode);

        Optional<MaterialDTO> material = materialService.getMaterial(materialCode);
        log.info("material : " + material);

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
