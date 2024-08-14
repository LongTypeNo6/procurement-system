package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.repository.WarehousingRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class WarehousingServiceImpl {

    private final WarehousingRepository warehousingRepository;
}
