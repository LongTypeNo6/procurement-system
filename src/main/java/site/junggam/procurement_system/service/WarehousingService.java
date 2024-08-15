package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.Warehousing;

public interface WarehousingService {

    //입고상세보기
    WarehousingDTO getWarehousing(String warehousingId);

    //입고대기리스트보기
    PageResultDTO<WarehousingDTO, Warehousing> getAllWarehousingList(PageRequestDTO pageRequestDTO);
}
