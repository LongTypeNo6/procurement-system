package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.WarehousingDTO;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingStatus;
import site.junggam.procurement_system.mapper.WarehousingMapper;
import site.junggam.procurement_system.repository.WarehousingRepository;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehousingServiceImpl implements WarehousingService {

    private final WarehousingRepository warehousingRepository;
    private final WarehousingMapper warehousingMapper;

    @Override
    public WarehousingDTO getWarehousing(String warehousingId) {
        Optional<Warehousing> result = warehousingRepository.findById(warehousingId);
        if (result.isPresent()) {
            return warehousingMapper.toDTO(result.get());
        }
        return null;
    }

    @Override
    public PageResultDTO<WarehousingDTO, Warehousing> getAllWarehousingList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaseOrder.purchaseOrderDate").ascending());
            Page<Warehousing> result = warehousingRepository.findAllByStatus(WarehousingStatus.PENDING, pageable);
            Function<Warehousing, WarehousingDTO> fn =(warehousing->{
                WarehousingDTO dto = warehousingMapper.toDTO(warehousing);
                return dto;
            });
            return new PageResultDTO<>(result,fn);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

}
