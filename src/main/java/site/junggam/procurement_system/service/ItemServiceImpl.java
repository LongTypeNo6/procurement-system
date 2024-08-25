package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.mapper.*;
import site.junggam.procurement_system.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;
    private final ProductBomRepository productBomRepository;
    private final ProductBomMapper productBomMapper;
    private final UnitBomRepository unitBomRepository;
    private final UnitBomMapper unitBomMapper;

    //코드 만들어내는 메소드
    private String generateCode(String itemCode) {
        String lastCode=null;
        if(itemCode.equals("BM")){
            lastCode=materialRepository.findMaxMaterialCode();
        }else if(itemCode.equals("BU")){
            lastCode=unitRepository.findMaxUnitCode();
        }else if(itemCode.equals("BP")){
            lastCode=productRepository.findMaxProductCode();
        }
        // 2. 만약 마지막 코드가 null이거나 다른 날짜라면 001로 시작
        String newSequence = "00001";
        if (lastCode!= null) {
            // 3. 마지막 코드의 일련번호 추출
            String lastSequence = lastCode.substring(3);
            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;
            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%05d", nextSequence);
        }
        // 6. 최종 코드를 생성
        return itemCode+"-"+ newSequence;
    }

    @Override
    @Transactional
    public String materialResister(MaterialDTO materialDTO) {
        String materialCode= generateCode("BM");
        materialDTO.setMaterialCode(materialCode);
        materialDTO.setMaterialDrawFile(materialCode);
        materialDTO.setMaterialEtcFile(materialCode);
        materialRepository.save(materialMapper.toEntity(materialDTO));
        return materialCode;
    }

    @Override
    public MaterialDTO materialGet(String materialCode) {
        log.info("자재상세보기");
        Optional<Material> result = materialRepository.findById(materialCode);
        return result.map(materialMapper::toDTO).orElse(null);
    }

    @Override
    public PageResultDTO<MaterialDTO, Material> materialList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나주에 바꿀것
            Page<Material> result = materialRepository.findAll(pageable);
            Function<Material, MaterialDTO> fn = (material -> {
                MaterialDTO dto = materialMapper.toDTO(material);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    @Transactional
    public String unitResister(UnitDTO unitDTO) {
        String unitCode= generateCode("BU");
        unitDTO.setUnitCode(unitCode);
        unitDTO.setUnitDrawFile(unitCode+"D");
        unitDTO.setUnitEtcFile(unitCode+"E");
        unitRepository.save(unitMapper.toEntity(unitDTO));
        List<UnitBomDTO> uitBomDTOList=unitDTO.getUnitBomDTOList();
        uitBomDTOList.forEach(unitBomDTO -> {
            unitBomDTO.setUnitCode(unitCode);
            unitBomRepository.save(unitBomMapper.toEntity(unitBomDTO));
        });
        return unitCode;
    }

    @Override
    public UnitDTO unitGet(String unitCode) {
        Optional<Unit> result = unitRepository.findById(unitCode);
        UnitDTO unitDTO=result.map(unitMapper::toDTO).orElse(null);
        unitDTO.setUnitBomDTOList(unitBomMapper.toDTOs(unitBomRepository.findByunit(Unit.builder().unitCode(unitCode).build())));
        return unitDTO;
    }

    @Override
    public PageResultDTO<UnitDTO, Unit> unitList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("unitCode").descending()); //나주에 바꿀것
            Page<Unit> result = unitRepository.findAll(pageable);
            Function<Unit, UnitDTO> fn = (unit -> {
                UnitDTO dto = unitMapper.toDTO(unit);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    @Transactional
    public String productResister(ProductDTO productDTO) {
        String productCode= generateCode("BP");
        productDTO.setProductCode(productCode);
        productDTO.setProductDrawFile(productCode+"D");
        productDTO.setProductEtcFile(productCode+"E");
        productRepository.save(productMapper.toEntity(productDTO));
        List<ProductBomDTO> productBomDTOList=productDTO.getProductBomDTOList();
        productBomDTOList.forEach(productBomDTO -> {
            productBomDTO.setProductCode(productCode);
            productBomRepository.save(productBomMapper.toEntity(productBomDTO));
        });
        return productCode;
    }

    @Override
    public ProductDTO productGet(String productCode) {
        Optional<Product> result = productRepository.findById(productCode);
        ProductDTO productDTO=result.map(productMapper::toDTO).orElse(null);
        productDTO.setProductBomDTOList(productBomMapper.toDTOs(productBomRepository.findByProduct(Product.builder().productCode(productCode).build())));
        return productDTO;
    }

    @Override
    public PageResultDTO<ProductDTO, Product> productList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("productCode").descending()); //나주에 바꿀것
            Page<Product> result = productRepository.findAll(pageable);
            Function<Product, ProductDTO> fn = (product -> {
                ProductDTO dto = productMapper.toDTO(product);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

}
