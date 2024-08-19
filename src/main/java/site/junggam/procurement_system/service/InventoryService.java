package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.TemMaterial;

public interface InventoryService {

    //창고재고 목록보기
    PageResultDTO<InventoryDTO, Inventory> getInventoryList(PageRequestDTO pageRequestDTO);

    //창고재고 총재고량 보기
    Integer getTotallMaterialQuantity();

    //창고재고 총재고량 보기
    Double getTotallMaterialPrice();
}
