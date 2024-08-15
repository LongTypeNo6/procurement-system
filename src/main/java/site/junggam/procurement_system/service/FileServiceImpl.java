package site.junggam.procurement_system.service;

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
    private String uploadDir;

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

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
    public List<String> saveFiles(MultipartFile[] files) {

        List<String> savedFileNames = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                uploadFile(file);
            }
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
        return savedFileNames;
    }

    @Override
    public FileDTO uploadFile(MultipartFile multipartFile) throws IOException {
        createDirectoryIfNotExists(uploadDir);
        String originalFilename = multipartFile.getOriginalFilename();
        String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        Path filePath = Paths.get(uploadDir, storedFilename);

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