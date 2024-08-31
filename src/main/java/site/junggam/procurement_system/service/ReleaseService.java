package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Release;

public interface ReleaseService {

    //출고요청상세보기
    ReleaseDTO getReleaseRequest(String releaseCode);

    //출고요청하기
    void saveReleaseRequest(ReleaseDTO releaseDTO);

    //출고요청리스트
    PageResultDTO<ReleaseDTO, Release> getReleaseList(PageRequestDTO pageRequestDTO);

    //출고하기
    void saveRelease(ReleaseDTO releaseDTO);
}
