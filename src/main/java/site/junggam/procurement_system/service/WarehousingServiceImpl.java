package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.dto.WarehousingHistoryDTO;
import site.junggam.procurement_system.entity.*;
import site.junggam.procurement_system.mapper.InventoryMapper;
import site.junggam.procurement_system.mapper.WarehousingHistoryMapper;
import site.junggam.procurement_system.mapper.WarehousingMapper;
import site.junggam.procurement_system.repository.InventoryHistoryRepository;
import site.junggam.procurement_system.repository.InventoryRepository;
import site.junggam.procurement_system.repository.WarehousingHistoryRepository;
import site.junggam.procurement_system.repository.WarehousingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehousingServiceImpl implements WarehousingService {

    private final WarehousingRepository warehousingRepository;
    private final WarehousingMapper warehousingMapper;

    private final WarehousingHistoryRepository warehousingHistoryRepository;
    private final WarehousingHistoryMapper warehousingHistoryMapper;
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryHistoryRepository inventoryHistoryRepository;


    @Override
    public WarehousingDTO getWarehousing(String warehousingId) {
        Optional<Warehousing> result = warehousingRepository.findById(warehousingId);
        if (result.isPresent()) {
            WarehousingDTO warehousingDTO=warehousingMapper.toDTO(result.get());
            warehousingDTO.setWarehousingHistoryDTOS(getWarehousingHistory(warehousingId));
            return warehousingDTO;
        }
        return null;
    }

    @Override
    public String saveWarehousingHistory(WarehousingHistoryDTO warehousingHistoryDTO) {

        //입고히스토리 정보 저장
        log.info("들어온 DTO값"+warehousingHistoryDTO);
        String warehousingCode=warehousingHistoryDTO.getWarehousingCode();
        int historyNum;
        historyNum=1+warehousingHistoryRepository.findByWarehousingCode(warehousingCode).size();
        String warehousingHistoryCode=warehousingCode+"-"+historyNum;
        warehousingHistoryDTO.setWarehousingHistoryCode(warehousingHistoryCode);

        warehousingHistoryRepository.save(warehousingHistoryMapper.toEntity(warehousingHistoryDTO));

        //인벤토리 저장
        String materialCode = warehousingHistoryDTO.getMaterialCode();
        LocalDateTime warehousingDateTime = warehousingHistoryDTO.getWarehousingDate();
        int warehousingQuantity = warehousingHistoryDTO.getWarehousingQuantity();
        Inventory inventory= inventoryRepository.findById(materialCode).get();
        //인벤토리 히스토리 먼저 저장하고
        InventoryHistory inventoryHistory=InventoryHistory.builder()
                .inventory(inventory)
                .transactionType(InventoryHistoryStatus.WAREHOUSING)
                .transactionReference(warehousingHistoryCode)
                .transactionDate(warehousingDateTime)
                .quantityChange(warehousingQuantity)
                .finalQuantity(inventory.getMaterialQuantity()+warehousingQuantity)
                .build();
        int finalQauntity =inventoryHistoryRepository.save(inventoryHistory).getFinalQuantity();
        //인벤토리 정보 변경
        inventory.setMaterialQuantity(finalQauntity);
        inventoryRepository.save(inventory);

        //여기는 입고 정보를 저장
        Warehousing warehousing=warehousingRepository.findById(warehousingCode).get();
        int orderQauntity=getWarehousing(warehousingCode).getPurchaseOrderDTO().getProcurementPlanQuantity();
        int warehousingQauntity = warehousingHistoryDTO.getWarehousingQuantity();
        boolean statusReult = warehousingHistoryDTO.getWarehousingHistoryStatus().equals(WarehousingHistoryStatus.WAREHOUSING);
        if(statusReult && orderQauntity==warehousingQauntity){
            warehousing.changeWarehousingStatus(WarehousingStatus.COMPLETED);
            warehousingRepository.save(warehousing);
        }
        return warehousingHistoryCode;
    }

    @Override
    public List<WarehousingHistoryDTO> getWarehousingHistory(String warehousingId) {
        List<WarehousingHistory> warehousingHistoryList=warehousingHistoryRepository.findByWarehousingCode(warehousingId);
        return warehousingHistoryMapper.toDtos(warehousingHistoryList);
    }

    @Override
    public PageResultDTO<WarehousingDTO, Warehousing> getAllWarehousingList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrder.purchaseOrderDate").ascending());
            Page<Warehousing> result = warehousingRepository.findAllByStatus(WarehousingStatus.PENDING, pageable);
            Function<Warehousing, WarehousingDTO> fn =(warehousing->{
                WarehousingDTO dto = warehousingMapper.toDTO(warehousing);
                return dto;
            });
            return new PageResultDTO<>(result,fn);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

}
