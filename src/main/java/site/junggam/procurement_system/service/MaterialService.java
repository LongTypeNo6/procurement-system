package site.junggam.procurement_system.service;

import site.junggam.procurement_system.dto.MaterialDTO;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    // 자재 등록 메소드
    String insertMaterial(MaterialDTO materialDTO);

    // 자재 수정 메소드
    void updateMaterial(MaterialDTO materialDTO);

    // 자재 삭제 메소드
    void deleteMaterial(String materialCode);

    // 자재 조회 메소드
    Optional<MaterialDTO> getMaterial(String materialCode);

    // 자재 목록 메소드
    //Page<MaterialDTO> getListMaterial(Pageable pageable);
    List<MaterialDTO> getListMaterial();

    // 자재 검색 메소드
    //Page<MaterialDTO> searchMaterial(String type, Pageable pageable);
    List<MaterialDTO> searchMaterial(String keyword);

}
