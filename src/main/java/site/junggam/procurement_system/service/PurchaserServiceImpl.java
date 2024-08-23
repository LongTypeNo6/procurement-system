package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.*;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.Product;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.mapper.PurchaserMapper;
import site.junggam.procurement_system.repository.PurchaserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class PurchaserServiceImpl implements PurchaserService {
    private final PurchaserRepository purchaserRepository;
    private final PurchaserMapper purchaserMapper;


    @Override
    public PurchaserDTO getPurchaser(String purchaserCode) {
        log.info("회사정보 상세보기 서비스");
        Optional<Purchaser> result = purchaserRepository.findById(purchaserCode);
        return result.map(purchaserMapper::toDto).orElse(null);
    }

    @Override
    public PageResultDTO<PurchaserDTO, Purchaser> getPurchaserList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("purchaserCode").descending()); //나주에 바꿀것
            Page<Purchaser> result = purchaserRepository.findAll(pageable);
            Function<Purchaser, PurchaserDTO> fn = (purchaser -> {
                PurchaserDTO dto = purchaserMapper.toDto(purchaser);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public void savePurchaser(PurchaserDTO purchaserDTO) {
        purchaserRepository.save(purchaserMapper.toEntity(purchaserDTO));
    }

    @Override
    public List<PurchaserDTO> getPurchaserSearch(String keyword) {
        try {
            List<Purchaser> result = purchaserRepository.findByIdAndName(keyword);
            return purchaserMapper.toDtos(result);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
