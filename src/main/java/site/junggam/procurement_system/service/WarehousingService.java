package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.dto.WarehousingHistoryDTO;
import site.junggam.procurement_system.entity.Warehousing;

import java.util.List;

public interface WarehousingService {

    //입고상세보기
    WarehousingDTO getWarehousing(String warehousingId);

    //입고처리(=입고내역추가+입고상태변경)
    String saveWarehousingHistory(WarehousingHistoryDTO warehousingHistoryDTO);

    //입고내역보기(히스토리)
    List<WarehousingHistoryDTO> getWarehousingHistory(String warehousingId);

    //입고대기리스트보기(조건=상태가 펜딩)
    PageResultDTO<WarehousingDTO, Warehousing> getAllWarehousingList(PageRequestDTO pageRequestDTO);

}
