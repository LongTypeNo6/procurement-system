package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.mapper.InventoryMapper;
import site.junggam.procurement_system.repository.InventoryRepository;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public PageResultDTO<InventoryDTO, Inventory> getInventoryList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("materialCode").descending()); //나주에 바꿀것
            Page<Inventory> result = inventoryRepository.findAll(pageable);
            Function<Inventory, InventoryDTO> fn = (inventory -> {
                InventoryDTO dto = inventoryMapper.toDTO(inventory);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public Integer getTotallMaterialQuantity() {
        return inventoryRepository.getTotallMaterialQuantity();
    }

    @Override
    public Double getTotallMaterialPrice() {
        return inventoryRepository.getTotallContractAvgPrice()*inventoryRepository.getTotallMaterialQuantity();
    }
}
