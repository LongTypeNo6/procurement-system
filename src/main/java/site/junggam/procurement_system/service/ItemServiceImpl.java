package site.junggam.procurement_system.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import site.junggam.procurement_system.dto.MaterialDTO;
import site.junggam.procurement_system.dto.UnitDTO;
import site.junggam.procurement_system.entity.Material;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.entity.Unit;
import site.junggam.procurement_system.mapper.*;
import site.junggam.procurement_system.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;
    private final ProductBomRepository productBomRepository;
    private final ProductBomMapper productBomMapper;
    private final UnitBomRepository unitBomRepository;
    private final UnitBomMapper unitBomMapper;

    //코드 만들어내는 메소드
    private String generateCode(String itemCode) {
        String lastCode=null;
        if(itemCode.equals("BM")){
            lastCode=materialRepository.findMaxMaterialCode();
        }else if(itemCode.equals("BU")){
            lastCode=unitRepository.findMaxUnitCode();
        }else if(itemCode.equals("BP")){
            lastCode=productRepository.findMaxProductCode();
        }
        // 2. 만약 마지막 코드가 null이거나 다른 날짜라면 001로 시작
        String newSequence = "00001";
        if (lastCode!= null) {
            // 3. 마지막 코드의 일련번호 추출
            String lastSequence = lastCode.substring(3);
            // 4. 일련번호를 숫자로 변환하고 1 증가시킴
            int nextSequence = Integer.parseInt(lastSequence) + 1;
            // 5. 새로운 일련번호를 3자리 형식으로 포맷 (예: 001, 002, ...)
            newSequence = String.format("%05d", nextSequence);
        }
        // 6. 최종 코드를 생성
        return itemCode+"-"+ newSequence;
    }

    @Override
    @Transactional
    public String materialResister(MaterialDTO materialDTO) {
        String materialCode= generateCode("BM");
        materialDTO.setMaterialCode(materialCode);
        materialDTO.setMaterialDrawFile(materialCode);
        materialDTO.setMaterialEtcFile(materialCode);
        materialRepository.save(materialMapper.toEntity(materialDTO));
        return materialCode;
    }

    @Override
    public MaterialDTO materialGet(String materialCode) {
        log.info("자재상세보기");
        Optional<Material> result = materialRepository.findById(materialCode);
        return result.map(materialMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional
    public String unitResister(UnitDTO unitDTO) {
        String unitCode= generateCode("BU");
        unitDTO.setUnitCode(unitCode);
        unitDTO.setUnitDrawFile(unitCode);
        unitDTO.setUnitEtcFile(unitCode);
        unitRepository.save(unitMapper.toEntity(unitDTO));
        return unitCode;
    }

    @Override
    public UnitDTO unitGet(String unitCode) {
        Optional<Unit> result = unitRepository.findById(unitCode);
        return result.map(unitMapper::toDTO).orElse(null);
    }

}
