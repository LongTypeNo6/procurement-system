package site.junggam.procurement_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.dto.WarehousingHistoryDTO;
import site.junggam.procurement_system.entity.AllFiles;
import site.junggam.procurement_system.entity.Warehousing;
import site.junggam.procurement_system.entity.WarehousingHistory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-29T15:26:00+0900",
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

        warehousingHistoryDTO.warehousingCode( warehousingHistoryWarehousingWarehousingCode( warehousingHistory ) );
        warehousingHistoryDTO.warehousingHistoryCode( warehousingHistory.getWarehousingHistoryCode() );
        warehousingHistoryDTO.warehousingDate( warehousingHistory.getWarehousingDate() );
        warehousingHistoryDTO.warehousingShipmentSpec( warehousingHistory.getWarehousingShipmentSpec() );
        warehousingHistoryDTO.warehousingSpec( warehousingHistory.getWarehousingSpec() );
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
        warehousingHistory.warehousingShipmentSpec( warehousingHistoryDTO.getWarehousingShipmentSpec() );
        warehousingHistory.warehousingSpec( warehousingHistoryDTO.getWarehousingSpec() );
        warehousingHistory.warehousingResultMemo( warehousingHistoryDTO.getWarehousingResultMemo() );
        warehousingHistory.warehousingQuantity( warehousingHistoryDTO.getWarehousingQuantity() );
        warehousingHistory.warehousingHistoryStatus( warehousingHistoryDTO.getWarehousingHistoryStatus() );

        return warehousingHistory.build();
    }

    @Override
    public List<AllFilesDTO> toAllFilesDtos(List<AllFiles> allFiles) {
        if ( allFiles == null ) {
            return null;
        }

        List<AllFilesDTO> list = new ArrayList<AllFilesDTO>( allFiles.size() );
        for ( AllFiles allFiles1 : allFiles ) {
            list.add( allFilesToAllFilesDTO( allFiles1 ) );
        }

        return list;
    }

    @Override
    public List<AllFiles> toAllFilesEntities(List<AllFilesDTO> allFilesDTOs) {
        if ( allFilesDTOs == null ) {
            return null;
        }

        List<AllFiles> list = new ArrayList<AllFiles>( allFilesDTOs.size() );
        for ( AllFilesDTO allFilesDTO : allFilesDTOs ) {
            list.add( allFilesDTOToAllFiles( allFilesDTO ) );
        }

        return list;
    }

    private String warehousingHistoryWarehousingWarehousingCode(WarehousingHistory warehousingHistory) {
        if ( warehousingHistory == null ) {
            return null;
        }
        Warehousing warehousing = warehousingHistory.getWarehousing();
        if ( warehousing == null ) {
            return null;
        }
        String warehousingCode = warehousing.getWarehousingCode();
        if ( warehousingCode == null ) {
            return null;
        }
        return warehousingCode;
    }

    protected Warehousing warehousingHistoryDTOToWarehousing(WarehousingHistoryDTO warehousingHistoryDTO) {
        if ( warehousingHistoryDTO == null ) {
            return null;
        }

        Warehousing.WarehousingBuilder warehousing = Warehousing.builder();

        warehousing.warehousingCode( warehousingHistoryDTO.getWarehousingCode() );

        return warehousing.build();
    }

    protected AllFilesDTO allFilesToAllFilesDTO(AllFiles allFiles) {
        if ( allFiles == null ) {
            return null;
        }

        AllFilesDTO.AllFilesDTOBuilder allFilesDTO = AllFilesDTO.builder();

        allFilesDTO.inum( allFiles.getInum() );
        allFilesDTO.fileName( allFiles.getFileName() );
        allFilesDTO.path( allFiles.getPath() );
        allFilesDTO.fileSize( allFiles.getFileSize() );
        allFilesDTO.fileType( allFiles.getFileType() );
        allFilesDTO.foreignCode( allFiles.getForeignCode() );

        return allFilesDTO.build();
    }

    protected AllFiles allFilesDTOToAllFiles(AllFilesDTO allFilesDTO) {
        if ( allFilesDTO == null ) {
            return null;
        }

        AllFiles.AllFilesBuilder allFiles = AllFiles.builder();

        allFiles.inum( allFilesDTO.getInum() );
        allFiles.fileName( allFilesDTO.getFileName() );
        allFiles.path( allFilesDTO.getPath() );
        allFiles.fileSize( allFilesDTO.getFileSize() );
        allFiles.fileType( allFilesDTO.getFileType() );
        allFiles.foreignCode( allFilesDTO.getForeignCode() );

        return allFiles.build();
    }
}
