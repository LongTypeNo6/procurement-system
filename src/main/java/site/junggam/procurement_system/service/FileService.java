package site.junggam.procurement_system.service;

import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.FileDTO;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileService {

    //폴더 만들기
    void createDirectoryIfNotExists(String directoryPath);

    // 파일 업로드
    List<String> saveFiles(MultipartFile[] files);
    FileDTO uploadFile(MultipartFile multipartFile) throws IOException;

    // 파일 목록 조회
    List<FileDTO> getFileList();

    // 파일 다운로드 경로 조회
    Path getFilePath(Long id);

    // 파일 삭제
    void deleteFile(Long id);

}
