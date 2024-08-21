package site.junggam.procurement_system.service;

import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.AllFilesDTO;

import java.util.List;

public interface AllFilesService {
    Long saveFile(AllFilesDTO allFilesDTO);
    List<AllFilesDTO> getFilesByForeignCode(String foreignCode);
    void deleteFile(Long inum);
    List<AllFilesDTO> uploadFiles(MultipartFile[] uploadFiles, String foreignCode, String subDirectory);
    AllFilesDTO uploadFile(MultipartFile uploadFile, String foreignCode, String subDirectory);
}
