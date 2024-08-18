package site.junggam.procurement_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.entity.AllFiles;
import site.junggam.procurement_system.mapper.AllFilesMapper;
import site.junggam.procurement_system.repository.AllFilesRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class AllFilesServiceImpl implements AllFilesService {

    private final AllFilesRepository allFilesRepository;
    private final AllFilesMapper allFilesMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private String setUploadDir(String subDirectory) {
        String str = subDirectory;
        String folderPath = str.replace("//", File.separator);
        File uploadPathFolder = new File(uploadDir, folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @Override
    public Long saveFile(AllFilesDTO allFilesDTO) {
        AllFiles allFiles = allFilesMapper.toEntity(allFilesDTO);
        allFilesRepository.save(allFiles);
        return allFiles.getInum();
    }

    @Override
    public List<AllFilesDTO> getFilesByForeignCode(String foreignCode) {
        List<AllFiles> files = allFilesRepository.findByForeignCode(foreignCode);
        return files.stream()
                .map(allFilesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFile(Long inum) {
        allFilesRepository.deleteById(inum);
    }

    @Override
    public List<AllFilesDTO> uploadFiles(MultipartFile[] uploadFiles, String foreignCode, String subDirectory) {
        List<AllFilesDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            String folderPath = setUploadDir(subDirectory);

            // 파일명 중복 처리
            fileName = resolveFileNameConflict(folderPath, fileName);

            String saveName = uploadDir + File.separator + folderPath + File.separator + fileName;
            Path savePath = Paths.get(saveName);

            try {
                // 원본 파일 저장
                uploadFile.transferTo(savePath);

                // 파일 정보 DB 저장
                AllFilesDTO allFilesDTO = AllFilesDTO.builder()
                        .fileName(fileName)
                        .path(folderPath)
                        .fileSize(uploadFile.getSize())
                        .fileType(uploadFile.getContentType())
                        .foreignCode(foreignCode) // 외부 코드 설정
                        .build();

                Long inum = this.saveFile(allFilesDTO);

                // DTO에 inum 값 설정
                allFilesDTO.setInum(inum);

                resultDTOList.add(allFilesDTO);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 업로드 실패", e);
            }

        }
        return resultDTOList;
    }


    private String resolveFileNameConflict(String folderPath, String fileName) {
        File file = new File(uploadDir + File.separator + folderPath + File.separator + fileName);
        int count = 1;

        // 파일명이 중복될 경우 뒤에 숫자를 추가
        while (file.exists()) {
            String newFileName = fileName.substring(0, fileName.lastIndexOf('.'))
                    + "(" + count + ")"
                    + fileName.substring(fileName.lastIndexOf('.'));
            file = new File(uploadDir + File.separator + folderPath + File.separator + newFileName);
            count++;
        }

        return file.getName();
    }

    private String makeTimeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);
        File uploadPathFolder = new File(uploadDir, folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
