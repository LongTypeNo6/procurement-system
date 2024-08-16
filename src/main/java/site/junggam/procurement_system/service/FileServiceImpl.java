package site.junggam.procurement_system.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.FileDTO;
import site.junggam.procurement_system.entity.FileEntity;
import site.junggam.procurement_system.mapper.FileMapper;
import site.junggam.procurement_system.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String basePath;

    private String uploadDir;

    @PostConstruct
    public void init() {
        this.uploadDir = basePath; // 기본 경로로 초기화
    }

    private void setUploadDir(String subDirectory) {
        this.uploadDir = Paths.get(basePath, subDirectory).toString(); // 서브 디렉토리 설정
    }

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    public boolean fileExists(String fileName) {
        Path filePath = Paths.get(uploadDir, fileName);
        return Files.exists(filePath);
    }

    @Override
    public void createDirectoryIfNotExists(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Could not create directory: " + directoryPath, e);
            }
        }
    }

    @Override
    public List<String> saveFilesWithId(MultipartFile[] files, boolean overwrite, String fileId, String subDirectory) {
        setUploadDir(subDirectory);
        List<String> savedFileNames = new ArrayList<>();
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String fileName = file.getOriginalFilename();  // 파일 이름은 원본 이름 그대로 사용
                uploadFileWithCustomName(file, fileName, overwrite);

                // 파일 정보를 DB에 저장
                FileEntity fileEntity = FileEntity.builder()
                        .fileCode(fileId + "-" + i)  // fileId는 여기서만 사용, 파일 이름에 사용하지 않음
                        .originalName(file.getOriginalFilename())
                        .storedName(fileName)
                        .filePath(Paths.get(uploadDir, fileName).toString()) // 올바른 경로 설정
                        .fileType(file.getContentType())
                        .fileSize(file.getSize())
                        .build();

                fileRepository.save(fileEntity);
                savedFileNames.add(fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
        return savedFileNames;
    }


    private void uploadFileWithCustomName(MultipartFile file, String fileName, boolean overwrite) throws IOException {
        createDirectoryIfNotExists(uploadDir);
        Path filePath = Paths.get(uploadDir, fileName);

        if (Files.exists(filePath)) {
            if (overwrite) {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                throw new RuntimeException("파일이 이미 존재합니다: " + fileName);
            }
        } else {
            Files.copy(file.getInputStream(), filePath);
        }
    }

    @Override
    public List<String> saveFiles(MultipartFile[] files, boolean overwrite) {
        List<String> savedFileNames = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                // 덮어쓰기 여부를 체크한 후 파일 업로드
                uploadFile(file, overwrite);
                savedFileNames.add(fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
        return savedFileNames;
    }


    @Override
    public FileDTO uploadFile(MultipartFile multipartFile, boolean overwrite) throws IOException {
        createDirectoryIfNotExists(uploadDir);
        String originalFilename = multipartFile.getOriginalFilename();
        String storedFilename = originalFilename;
        Path filePath = Paths.get(uploadDir, storedFilename);

        // 파일 중복 여부 확인
        if (Files.exists(filePath)) {
            if (overwrite) {
                // 기존 파일 정보를 업데이트
                FileEntity existingFileEntity = fileRepository.findByOriginalName(originalFilename)
                        .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다: " + originalFilename));

                Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                existingFileEntity.setStoredName(storedFilename);
                existingFileEntity.setFilePath(filePath.toString());
                existingFileEntity.setFileType(multipartFile.getContentType());
                existingFileEntity.setFileSize(multipartFile.getSize());

                FileEntity updatedFile = fileRepository.save(existingFileEntity);
                return fileMapper.toDTO(updatedFile);
            } else {
                throw new RuntimeException("파일이 이미 존재합니다: " + originalFilename);
            }
        }

        Files.copy(multipartFile.getInputStream(), filePath);

        FileEntity fileEntity = FileEntity.builder()
                .originalName(originalFilename)
                .storedName(storedFilename)
                .filePath(filePath.toString())
                .fileType(multipartFile.getContentType())
                .fileSize(multipartFile.getSize())
                .build();

        FileEntity savedFile = fileRepository.save(fileEntity);
        return fileMapper.toDTO(savedFile);
    }

    @Override
    public List<FileDTO> getFileList() {
        return fileRepository.findAll().stream()
                .map(fileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Path getFilePath(Long id) {
        return fileRepository.findById(id)
                .map(fileEntity -> Paths.get(fileEntity.getFilePath()))
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public void deleteFile(Long id) {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Path filePath = Paths.get(fileEntity.getFilePath());
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file", e);
        }

        fileRepository.delete(fileEntity);
    }
}