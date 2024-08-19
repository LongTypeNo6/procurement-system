package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.TemMaterialDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.TemMaterial;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingStatus;
import site.junggam.procurement_system.mapper.TemMaterialMapper;
import site.junggam.procurement_system.repository.TemMaterialRepository;

import java.util.List;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class TemMaterialServiceImpl implements TemMaterialService {

    private final TemMaterialRepository temMaterialRepository;
    private final TemMaterialMapper temMaterialMapper;

    @Override
    public List<TemMaterialDTO> getTemMaterialList(String keyword) {
        try {
            List<TemMaterial> result = temMaterialRepository.findByIdAndName(keyword);
            return temMaterialMapper.toDTOs(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
