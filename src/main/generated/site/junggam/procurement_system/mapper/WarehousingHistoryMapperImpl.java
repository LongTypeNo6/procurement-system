package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.WarehousingHistoryDTO;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingHistory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T01:50:08+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class WarehousingHistoryMapperImpl implements WarehousingHistoryMapper {

    @Override
    public WarehousingHistoryDTO toDto(WarehousingHistory warehousingHistory) {
        if ( warehousingHistory == null ) {
            return null;
        }

        WarehousingHistoryDTO.WarehousingHistoryDTOBuilder warehousingHistoryDTO = WarehousingHistoryDTO.builder();

        warehousingHistoryDTO.warehousingHistoryCode( warehousingHistory.getWarehousingHistoryCode() );
        warehousingHistoryDTO.warehousingDate( warehousingHistory.getWarehousingDate() );
        warehousingHistoryDTO.warehousingResultMemo( warehousingHistory.getWarehousingResultMemo() );
        warehousingHistoryDTO.warehousingQuantity( warehousingHistory.getWarehousingQuantity() );
        warehousingHistoryDTO.warehousingHistoryStatus( warehousingHistory.getWarehousingHistoryStatus() );

        return warehousingHistoryDTO.build();
    }

    @Override
    public List<WarehousingHistoryDTO> toDtos(List<WarehousingHistory> warehousingHistories) {
        if ( warehousingHistories == null ) {
            return null;
        }

        List<WarehousingHistoryDTO> list = new ArrayList<WarehousingHistoryDTO>( warehousingHistories.size() );
        for ( WarehousingHistory warehousingHistory : warehousingHistories ) {
            list.add( toDto( warehousingHistory ) );
        }

        return list;
    }

    @Override
    public WarehousingHistory toEntity(WarehousingHistoryDTO warehousingHistoryDTO) {
        if ( warehousingHistoryDTO == null ) {
            return null;
        }

        WarehousingHistory.WarehousingHistoryBuilder warehousingHistory = WarehousingHistory.builder();

        warehousingHistory.warehousing( warehousingHistoryDTOToWarehousing( warehousingHistoryDTO ) );
        warehousingHistory.warehousingHistoryCode( warehousingHistoryDTO.getWarehousingHistoryCode() );
        warehousingHistory.warehousingDate( warehousingHistoryDTO.getWarehousingDate() );
        warehousingHistory.warehousingResultMemo( warehousingHistoryDTO.getWarehousingResultMemo() );
        warehousingHistory.warehousingQuantity( warehousingHistoryDTO.getWarehousingQuantity() );
        warehousingHistory.warehousingHistoryStatus( warehousingHistoryDTO.getWarehousingHistoryStatus() );

        return warehousingHistory.build();
    }

    protected Warehousing warehousingHistoryDTOToWarehousing(WarehousingHistoryDTO warehousingHistoryDTO) {
        if ( warehousingHistoryDTO == null ) {
            return null;
        }

        Warehousing.WarehousingBuilder warehousing = Warehousing.builder();

        warehousing.warehousingCode( warehousingHistoryDTO.getWarehousingCode() );

        return warehousing.build();
    }
}
