package site.junggam.procurement_system.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryEnhancerFactory;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.*;
import site.junggam.procurement_system.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static site.junggam.procurement_system.entity.QMaterial.material;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

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
    private final InventoryRepository inventoryRepository;

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
        Material material = materialMapper.toEntity(materialDTO);
        inventoryRepository.save(Inventory.builder().material(materialRepository.save(material)).build());
        return materialCode;
    }

    @Override
    public MaterialDTO materialGet(String materialCode) {
        log.info("자재상세보기");
        Optional<Material> result = materialRepository.findById(materialCode);
        return result.map(materialMapper::toDTO).orElse(null);
    }

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<MaterialDTO, Material> materialList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나주에 바꿀것
//            Page<Material> result = materialRepository.findAll(pageable);
//            Function<Material, MaterialDTO> fn = (material -> {
//                MaterialDTO dto = materialMapper.toDTO(material);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QMaterial qMaterial = material;
            QContract qContract = QContract.contract;
            QEstimate qEstimate = QEstimate.estimate;
            QInventory qInventory = QInventory.inventory;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Material> query = queryFactory.selectFrom(qMaterial)
                    .where(getMaterialSearch(pageRequestDTO))
                    .orderBy(qMaterial.materialRegDate.desc());

            // 페이지 처리
            List<Material> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Material, MaterialDTO> fn = material -> {
              MaterialDTO dto  = materialMapper.toDTO(material);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getMaterialSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();

        QMaterial qMaterial = material;
        QContract qContract = QContract.contract;
        QEstimate qEstimate = QEstimate.estimate;
        QInventory qInventory = QInventory.inventory;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qMaterial.materialCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qMaterial.materialRegDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder builder1 = new BooleanBuilder();

            if (type.contains("1")) {
                BooleanBuilder nameSearchBuilder = new BooleanBuilder();
                if (keyword != null) {
                    nameSearchBuilder.or(qMaterial.materialName.contains(keyword));
                }
                builder1.and(nameSearchBuilder);
            }

            if (type.contains("2")) {
                builder1.or(qMaterial.materialCode.contains(keyword));
            }

            if (type.contains("3")) {
                builder1.or(qMaterial.materialStand.contains(keyword));
                builder1.or(qMaterial.materialTexture.contains(keyword));
            }

            builder.and(builder1);
        }

        return builder;
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

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<UnitDTO, Unit> unitList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("unitCode").descending()); //나주에 바꿀것
//            Page<Unit> result = unitRepository.findAll(pageable);
//            Function<Unit, UnitDTO> fn = (unit -> {
//                UnitDTO dto = unitMapper.toDTO(unit);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("unitCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QUnit qUnit = QUnit.unit;
            QMaterial qMaterial = material;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Unit> query = queryFactory.selectFrom(qUnit)
                    //.leftJoin(qUnit.unit, qUnit)
                    .where(getUnitSearch(pageRequestDTO))
                    .orderBy(qUnit.unitRegDate.desc());

            // 페이지 처리
            List<Unit> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Unit, UnitDTO> fn = unit -> {
                UnitDTO dto = unitMapper.toDTO(unit);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getUnitSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();

        QUnit qUnit = QUnit.unit;
        QMaterial qMaterial = material;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUnit.unitCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qUnit.unitRegDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder builder1 = new BooleanBuilder();

            if (type.contains("1")) {
                BooleanBuilder nameSearchBuilder = new BooleanBuilder();
                if (keyword != null) {
                    nameSearchBuilder.or(qUnit.unitName.contains(keyword));
                }
                builder1.and(nameSearchBuilder);
            }

            if (type.contains("2")) {
                builder1.or(qUnit.unitCode.contains(keyword));
            }

            if (type.contains("3")) {
                builder1.or(qUnit.unitStand.contains(keyword));
                builder1.or(qUnit.unitTexture.contains(keyword));
            }

            builder.and(builder1);
        }

        return builder;
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

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<ProductDTO, Product> productList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("productCode").descending()); //나주에 바꿀것
//            Page<Product> result = productRepository.findAll(pageable);
//            Function<Product, ProductDTO> fn = (product -> {
//                ProductDTO dto = productMapper.toDTO(product);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        } catch (Exception e) {
//            log.error("에러메세지", e);
//            throw e; // or handle the exception appropriately
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("productCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QProduct qProduct = QProduct.product;
            QUnit qUnit = QUnit.unit;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
                    //.leftJoin(qProduct.product, qProduct)
                    .where(getProductSearch(pageRequestDTO))
                    .orderBy(qProduct.productRegDate.desc());

            // 페이지 처리
            List<Product> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Product, ProductDTO> fn = product -> {
                ProductDTO dto = productMapper.toDTO(product);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getProductSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        //LocalDate startDate2 = pageRequestDTO.getStartDate2();
        //LocalDate endDate2 = pageRequestDTO.getEndDate2();

        QProduct qProduct = QProduct.product;
        QUnit qUnit = QUnit.unit;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProduct.productCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qProduct.productRegDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder builder1 = new BooleanBuilder();

            if (type.contains("1")) {
                BooleanBuilder nameSearchBuilder = new BooleanBuilder();
                if (keyword != null) {
                    nameSearchBuilder.or(qProduct.productName.contains(keyword));
                }
                builder1.and(nameSearchBuilder);
            }

            if (type.contains("2")) {
                builder1.or(qProduct.productCode.contains(keyword));
            }

            if (type.contains("3")) {
                builder1.or(qProduct.productStand.contains(keyword));
                builder1.or(qProduct.productTexture.contains(keyword));
            }

            builder.and(builder1);
        }

        return builder;
    }



}
