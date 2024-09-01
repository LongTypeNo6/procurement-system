package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.InventoryHistory;
import site.junggam.procurement_system.entity.TemMaterial;

public interface InventoryService {

    //창고재고 목록보기
    PageResultDTO<InventoryDTO, Inventory> getInventoryList(PageRequestDTO pageRequestDTO);

    //창고재고 총재고량 보기
    Integer getTotallMaterialQuantity();

    //창고재고 총재고량 보기
    Double getTotallMaterialPrice();

    //입출고내역 보기
    PageResultDTO<InventoryHistoryDTO, InventoryHistory> getInventoryHistoryList(PageRequestDTO pageRequestDTO);
}
