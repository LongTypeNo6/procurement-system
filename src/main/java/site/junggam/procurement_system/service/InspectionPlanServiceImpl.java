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
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.InspectionPlanDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.InspectionPlanMapper;
import site.junggam.procurement_system.repository.InspectionPlanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class InspectionPlanServiceImpl implements InspectionPlanService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final InspectionPlanRepository inspectionPlanRepository;
    private final InspectionPlanMapper inspectionPlanMapper;

    @Override
    public int getInspectionPlanCount(PurchaseOrder purchaseOrder) {
        List<InspectionPlan> inspectionPlans = inspectionPlanRepository.findByPurchaseOrder(purchaseOrder);
        long count = inspectionPlans.stream()
                .filter(plan -> !plan.getInspectionPlanDeliveryProgress().equals(InspectionPlanDeliveryProgress.NOT_INSPECTED))
                .count();
        return (int) count;
    }

    @Override
    @Transactional
    public List<InspectionPlanDTO> getInspectionPlan(String purchaseOrderCode) {
        List<InspectionPlan> result = inspectionPlanRepository.findByPurchaseOrder(PurchaseOrder.builder().purchaseOrderCode(purchaseOrderCode).build());
        return inspectionPlanMapper.toDTOs(result);
    }

    @Override
    public void saveInspectionPlan(InspectionPlanDTO inspectionPlanDTO) {
        inspectionPlanRepository.save(inspectionPlanMapper.toEntity(inspectionPlanDTO));
    }

    @Override
    public void modifyInspectionPlan(InspectionPlanDTO inspectionPlanDTO) {
        Optional<InspectionPlan> result
                = inspectionPlanRepository.findById(inspectionPlanDTO.getInspectionPlanCode());
        if (result.isPresent()) {
            InspectionPlan inspectionPlan = result.get();
            inspectionPlan.changeInspectionPlanDateTime(inspectionPlanDTO.getInspectionPlanDateTime());
            inspectionPlan.changeInspectionPlanMemo(inspectionPlanDTO.getInspectionPlanMemo());
            inspectionPlanRepository.save(inspectionPlan);
        }
    }

    @Override
    public void saveInspectionResult(InspectionPlanDTO inspectionPlanDTO) {
        Optional<InspectionPlan> result
                = inspectionPlanRepository.findById(inspectionPlanDTO.getInspectionPlanCode());
        if (result.isPresent()) {
            InspectionPlan inspectionPlan = result.get();
            inspectionPlan.changeInspectionResultDateTime(inspectionPlanDTO.getInspectionResultDateTime());
            inspectionPlan.changeInspectionPlanProgress(inspectionPlanDTO.getInspectionPlanProgress());
            inspectionPlan.changeInspectionPlanStatus(inspectionPlanDTO.getInspectionPlanStatus());
            inspectionPlan.changeInspectionPlanComplementary(inspectionPlanDTO.getInspectionPlanComplementary());
            inspectionPlan.changeInspectionPlanDeliveryProgress(inspectionPlanDTO.getInspectionPlanDeliveryProgress());
            inspectionPlanRepository.save(inspectionPlan);
        }
    }

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlanList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("PurchaseOrder.purchaseOrderCode").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게
//            Page<InspectionPlan> result = inspectionPlanRepository.findAll(pageable);
//
//            Function<InspectionPlan, InspectionPlanDTO> fn = (inspectionPlan -> {
//                InspectionPlanDTO dto =inspectionPlanMapper.toDTO(inspectionPlan);
//                int inspectionPlanCount = getInspectionPlanCount(inspectionPlan.getPurchaseOrder());
//                dto.setInspectionPlanCount(inspectionPlanCount);
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        }catch (Exception e) {
//            log.error(e);
//            throw e;
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("PurchaseOrder.purchaseOrderCode").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게

            // Q 클래스들 가져오기
            QInspectionPlan qInspectionPlan = QInspectionPlan.inspectionPlan;
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
            QMaterial qMaterial = QMaterial.material;
            QPurchaser qPurchaser = QPurchaser.purchaser;
            QProduct qProduct = QProduct.product;
            QUnit qUnit = QUnit.unit;
            QProductBom qProductBom = QProductBom.productBom;
            QUnitBom qUnitBom = QUnitBom.unitBom;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<InspectionPlan> query = queryFactory.selectFrom(qInspectionPlan)
                    .where(getInspectionPlanSearch(pageRequestDTO))
                    .orderBy(qInspectionPlan.purchaseOrder.purchaseOrderCode.desc());

            // 페이지 처리
            List<InspectionPlan> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<InspectionPlan, InspectionPlanDTO> fn = inspectionPlan -> {
                InspectionPlanDTO dto = inspectionPlanMapper.toDTO(inspectionPlan);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        }catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getInspectionPlanSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        LocalDate startDate2 = pageRequestDTO.getStartDate2();
        LocalDate endDate2 = pageRequestDTO.getEndDate2();
        LocalDate startDate3 = pageRequestDTO.getStartDate3();
        LocalDate endDate3 = pageRequestDTO.getEndDate3();

        QInspectionPlan qInspectionPlan = QInspectionPlan.inspectionPlan;
        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
        QMaterial qMaterial = QMaterial.material;
        QPurchaser qPurchaser = QPurchaser.purchaser;
        QProduct qProduct = QProduct.product;
        QUnit qUnit = QUnit.unit;
        QProductBom qProductBom = QProductBom.productBom;
        QUnitBom qUnitBom = QUnitBom.unitBom;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInspectionPlan.purchaseOrder.purchaseOrderCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qInspectionPlan.purchaseOrder.purchaseOrderDate.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }
        if (startDate2 != null && endDate2 != null) {
            builder.and(qInspectionPlan.purchaseOrder.procurementPlan.procurementPlanDeadLine.between(startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay()));
        }
        if (startDate3 != null && endDate3 != null) {
            builder.and(qInspectionPlan.inspectionPlanDateTime.between(startDate3.atStartOfDay(), endDate3.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder typeBuilder = new BooleanBuilder();

            // Ensure that qWarehousing.purchaseOrder.procurementPlan.material is not null
            if (type.contains("1")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.materialName.contains(keyword));
                }
            }

            if (type.contains("2")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.materialCode.contains(keyword));
                }
            }

            if (type.contains("3")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null &&
                        qInspectionPlan.purchaseOrder.procurementPlan.material.contract != null &&
                        qInspectionPlan.purchaseOrder.procurementPlan.material.contract.purchaser != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.contract.purchaser.purchaserName.contains(keyword));
                }
            }

            //if (type.contains("4")) {
            //    if (keyword != null) {
            //        //검수자(4) 조건은 생략..
            //    }
            //}

            builder.and(typeBuilder);
        }

        return builder;
    }

    private int preInspectionPlanProgress(String inspectionPlanCode){
        String codeExcludingNum=inspectionPlanCode.substring(0,inspectionPlanCode.length()-1);
        int preNum=Integer.parseInt(inspectionPlanCode.substring(inspectionPlanCode.length()-1))-1;
        if(preNum==0){
            return 0;
        }else {
            String preInspectionPlanCode=codeExcludingNum+preNum;
            return inspectionPlanRepository.findById(preInspectionPlanCode).get().getInspectionPlanProgress();
        }
    }


    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<InspectionPlanDTO, InspectionPlan> getInspectionPlaScheduleList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("inspectionPlanDateTime").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게
//            Page<InspectionPlan> result = inspectionPlanRepository.findAll(pageable);
//
//            Function<InspectionPlan, InspectionPlanDTO> fn = (inspectionPlan -> {
//                InspectionPlanDTO dto =inspectionPlanMapper.toDTO(inspectionPlan);
//                int inspectionPlanCount = getInspectionPlanCount(inspectionPlan.getPurchaseOrder());
//                dto.setInspectionPlanCount(inspectionPlanCount);
//                dto.setPreviousInspectionPlanProgress(preInspectionPlanProgress(inspectionPlan.getInspectionPlanCode()));
//                return dto;
//            });
//            return new PageResultDTO<>(result, fn);
//        }catch (Exception e) {
//            log.error(e);
//            throw e;
//        }

        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("PurchaseOrder.purchaseOrderCode").ascending()); //여기는 꼭 발주코드로 정렬해야함! 다른조건 추가시에도 발주코드정렬로 마무리되게

            // Q 클래스들 가져오기
            QInspectionPlan qInspectionPlan = QInspectionPlan.inspectionPlan;
            QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
            QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
            QMaterial qMaterial = QMaterial.material;
            QPurchaser qPurchaser = QPurchaser.purchaser;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<InspectionPlan> query = queryFactory.selectFrom(qInspectionPlan)
                    .where(getInspectionPlanScheduleSearch(pageRequestDTO))
                    .orderBy(qInspectionPlan.purchaseOrder.purchaseOrderCode.desc());

            // 페이지 처리
            List<InspectionPlan> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<InspectionPlan, InspectionPlanDTO> fn = inspectionPlan -> {
                InspectionPlanDTO dto = inspectionPlanMapper.toDTO(inspectionPlan);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getInspectionPlanScheduleSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        LocalDate startDate1 = pageRequestDTO.getStartDate1();
        LocalDate endDate1 = pageRequestDTO.getEndDate1();
        LocalDate startDate2 = pageRequestDTO.getStartDate2();
        LocalDate endDate2 = pageRequestDTO.getEndDate2();

        QInspectionPlan qInspectionPlan = QInspectionPlan.inspectionPlan;
        QPurchaseOrder qPurchaseOrder = QPurchaseOrder.purchaseOrder;
        QProcurementPlan qProcurementPlan = QProcurementPlan.procurementPlan;
        QMaterial qMaterial = QMaterial.material;
        QPurchaser qPurchaser = QPurchaser.purchaser;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInspectionPlan.purchaseOrder.purchaseOrderCode.contains("-"));  // 기본 조건

        if (startDate1 != null && endDate1 != null) {
            builder.and(qInspectionPlan.inspectionPlanDateTime.between(startDate1.atStartOfDay(), endDate1.plusDays(1).atStartOfDay()));
        }
        if (startDate2 != null && endDate2 != null) {
            builder.and(qInspectionPlan.purchaseOrder.procurementPlan.procurementPlanDeadLine.between(startDate2.atStartOfDay(), endDate2.plusDays(1).atStartOfDay()));
        }

        if (type != null) {
            BooleanBuilder typeBuilder = new BooleanBuilder();

            // Ensure that qInspectionPlan.purchaseOrder.procurementPlan.material is not null
            if (type.contains("1")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.materialName.contains(keyword));
                }
            }

            if (type.contains("2")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.materialCode.contains(keyword));
                }
            }

            if (type.contains("3")) {
                if (keyword != null && qInspectionPlan.purchaseOrder.procurementPlan.material != null &&
                        qInspectionPlan.purchaseOrder.procurementPlan.material.contract != null &&
                        qInspectionPlan.purchaseOrder.procurementPlan.material.contract.purchaser != null) {
                    typeBuilder.or(qInspectionPlan.purchaseOrder.procurementPlan.material.contract.purchaser.purchaserName.contains(keyword));
                }
            }

            builder.and(typeBuilder);
        }

        return builder;
    }


}
