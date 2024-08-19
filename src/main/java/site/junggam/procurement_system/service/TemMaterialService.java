package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.TemMaterial;
import site.junggam.procurement_system.entity.Warehousing;

import java.util.List;

public interface TemMaterialService {

    //검색리스트(조건=상태가 펜딩)
    List<TemMaterialDTO> getTemMaterialList(String keyword);
}
