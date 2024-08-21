package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.PurchaseOrderDTO;
import site.junggam.procurement_system.dto.PurchaserDTO;
import site.junggam.procurement_system.entity.PurchaseOrder;
import site.junggam.procurement_system.entity.Purchaser;

import java.util.List;

public interface PurchaserService {

    //거래처 상세보기
    PurchaserDTO getPurchaser(String purchaserCode);

    //거래처리스트 보기
    PageResultDTO<PurchaserDTO, Purchaser> getPurchaserList(PageRequestDTO pageRequestDTO);

    //거래처 저장
    void savePurchaser(PurchaserDTO purchaserDTO);

    //거래처 검색결과
    List<PurchaserDTO> getPurchaserSearch(String keyword);
}
