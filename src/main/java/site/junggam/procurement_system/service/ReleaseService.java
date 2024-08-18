package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.ReleaseDTO;

public interface ReleaseService {

    //출고요청상세보기
    ReleaseDTO getReleaseRequest(String releaseCode);
}
