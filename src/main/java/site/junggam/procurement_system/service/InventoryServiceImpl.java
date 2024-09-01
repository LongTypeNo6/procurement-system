package site.junggam.procurement_system.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.ContractMapper;
import site.junggam.procurement_system.mapper.InventoryHistoryMapper;
import site.junggam.procurement_system.mapper.InventoryMapper;
import site.junggam.procurement_system.repository.InventoryHistoryRepository;
import site.junggam.procurement_system.repository.InventoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    //이하 지피티 코드
    private final JPAQueryFactory queryFactory;

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    private final InventoryHistoryRepository inventoryHistoryRepository;
    private final InventoryHistoryMapper inventoryHistoryMapper;

    //CYH : 24.08.30 수정
    @Override
    public PageResultDTO<InventoryDTO, Inventory> getInventoryList(PageRequestDTO pageRequestDTO) {
//        try {
//            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나주에 바꿀것
//            Page<Inventory> result = inventoryRepository.findAll(pageable);
//            Function<Inventory, InventoryDTO> fn = (inventory -> {
//                InventoryDTO dto = inventoryMapper.toDTO(inventory);
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
            QInventory qInventory = QInventory.inventory;
            QMaterial qMaterial = QMaterial.material;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<Inventory> query = queryFactory.selectFrom(qInventory)
                    .where(getInventorySearch(pageRequestDTO))
                    .orderBy(qInventory.materialCode.desc());

            // 페이지 처리
            List<Inventory> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<Inventory, InventoryDTO> fn = inventory -> {
               InventoryDTO dto = inventoryMapper.toDTO(inventory);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    @Override
    public PageResultDTO<InventoryDTO, Inventory> getInventoryListOrderByPrice(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.unsorted()); //나주에 바꿀것
            Page<Inventory> result = inventoryRepository.findAllOrderByTotalPriceDesc(pageable);
            Function<Inventory, InventoryDTO> fn = (inventory -> {
                InventoryDTO dto = inventoryMapper.toDTO(inventory);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e;
        }
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getInventorySearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        QInventory qInventory = QInventory.inventory;
        QMaterial qMaterial = QMaterial.material;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInventory.materialCode.contains("-"));  // 기본 조건

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

            builder.and(builder1);
        }

        return builder;
    }

    @Override
    public Integer getTotallMaterialQuantity() {
        return inventoryRepository.getTotallMaterialQuantity();
    }


    @Override
    public Double getTotallMaterialPrice() {
        return inventoryRepository.getTotallContractAvgPrice();
    }


    @Override
    public PageResultDTO<InventoryHistoryDTO, InventoryHistory> getInventoryHistoryList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("inventoryHistoryCode").descending()); //나중에 바꿀것

            // Q 클래스들 가져오기
            QInventory qInventory = QInventory.inventory;
            QInventoryHistory qInventoryHistory = QInventoryHistory.inventoryHistory;
            QMaterial qMaterial = QMaterial.material;

            // QueryDSL의 JPAQuery 사용
            JPAQuery<InventoryHistory> query = queryFactory.selectFrom(qInventoryHistory)
                    .where(getInventoryHistorySearch(pageRequestDTO))
                    .orderBy(qInventoryHistory.inventoryHistoryCode.desc());

            // 페이지 처리
            List<InventoryHistory> results = query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = query.fetchCount();  // 총 카운트 계산

            Function<InventoryHistory, InventoryHistoryDTO> fn = inventoryHistory -> {
                InventoryHistoryDTO dto = inventoryHistoryMapper.toDto(inventoryHistory);
                return dto;
            };

            return new PageResultDTO<>(new PageImpl<>(results, pageable, total), fn);

        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    @Override
    public PageResultDTO<InventoryHistoryDTO, InventoryHistory> getInventoryHistoryListWithMaterial(PageRequestDTO pageRequestDTO, String materialCode) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("inventoryHistoryCode").descending()); //나주에 바꿀것
            Page<InventoryHistory> result = inventoryHistoryRepository.findAllByMaterialCode(materialCode,pageable);
            Function<InventoryHistory, InventoryHistoryDTO> fn = (inventoryHistory -> {
                InventoryHistoryDTO dto = inventoryHistoryMapper.toDto(inventoryHistory);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }

    }

    @Override
    public InventoryDTO getInventoryHistoryWithMaterial(String materialCode) {
        return inventoryMapper.toDTO(inventoryRepository.findById(materialCode).get());
    }

    //CYH : 24.08.30 추가
    private BooleanBuilder getInventoryHistorySearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        QInventory qInventory = QInventory.inventory;
        QInventoryHistory qInventoryHistory = QInventoryHistory.inventoryHistory;
        QMaterial qMaterial = QMaterial.material;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qInventoryHistory.inventoryHistoryCode.isNotNull());  // 기본 조건

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

            builder.and(builder1);
        }

        return builder;
    }

}
