package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.ReleaseDTO;
import site.junggam.procurement_system.entity.Release;
import site.junggam.procurement_system.mapper.ReleaseMapper;
import site.junggam.procurement_system.repository.ReleaseRepository;

import java.util.Optional;

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
}
