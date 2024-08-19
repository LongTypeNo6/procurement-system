package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.InventoryDTO;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Inventory;
import site.junggam.procurement_system.entity.Release;
import site.junggam.procurement_system.mapper.ReleaseMapper;
import site.junggam.procurement_system.repository.ReleaseRepository;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;


    @Override
    public ReleaseDTO getReleaseRequest(String releaseCode) {
        log.info("출고요청상세보기");

        Optional<Release> release = releaseRepository.findById(releaseCode);
        if (release.isPresent()) {
            ReleaseDTO releaseDTO = releaseMapper.toDTO(release.get());
            return releaseDTO;
        }else return null;
    }

    @Override
    public void saveReleaseRequest(ReleaseDTO releaseDTO) {
        releaseRepository.save(releaseMapper.toEntity(releaseDTO));
    }

    @Override
    public PageResultDTO<ReleaseDTO, Release> getReleaseList(PageRequestDTO pageRequestDTO) {
        try {
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("releaseCode").descending()); //나주에 바꿀것
            Page<Release> result = releaseRepository.findAll(pageable);
            Function<Release, ReleaseDTO> fn = (release -> {
                ReleaseDTO dto = releaseMapper.toDTO(release);
                return dto;
            });
            return new PageResultDTO<>(result, fn);
        } catch (Exception e) {
            log.error("에러메세지", e);
            throw e; // or handle the exception appropriately
        }
    }
}
