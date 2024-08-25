package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.MaterialDTO;

public interface ItemService {

    //자재등록하기
    String materialResister(MaterialDTO materialDTO);

    //자재상세보기
    MaterialDTO materialGet(String materialCode);

}
