package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.entity.AllFiles;
import site.junggam.procurement_system.repository.AllFilesRepository;
import site.junggam.procurement_system.service.AllFilesService;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class AllFilesController {

    private final AllFilesService allFilesService;
    private final AllFilesRepository allFilesRepository;

    @PostMapping("/upload/{foreignCode}/{subDirectory}")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @PathVariable("foreignCode") String foreignCode,
            @PathVariable("subDirectory") String subDirectory) {
        try {
            // 파일 업로드 처리
            List<AllFilesDTO> uploadedFiles = allFilesService.uploadFiles(files, foreignCode, subDirectory);
            return new ResponseEntity<>(uploadedFiles, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("파일 업로드 중 오류 발생: {}", e.getMessage());
            return new ResponseEntity<>("파일 업로드 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{foreignCode}")
    public ResponseEntity<List<AllFilesDTO>> getFilesByForeignCode(@PathVariable("foreignCode") String foreignCode) {
        log.info("파일목록을 위한 코드는"+foreignCode);
        List<AllFilesDTO> files = allFilesService.getFilesByForeignCode(foreignCode);
        log.info("파일 목록들"+files);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        // 파일 정보를 DB에서 조회
        AllFiles file = allFilesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));

        // 실제 파일 경로 (디코딩된 파일 이름 사용)
        Path filePath = Paths.get(file.getPath(), file.getFileName());
        Resource resource = new FileSystemResource(filePath.toFile());

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 다운로드 시 사용될 파일 이름을 인코딩 (브라우저에서 파일명이 깨지지 않도록)
        String encodedFileName;
        try {
            encodedFileName = URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8.toString())
                    .replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("파일 이름 인코딩 중 오류 발생", e);
        }

        // Content-Disposition 헤더 설정 (파일명을 명시적으로 설정)
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, file.getFileType());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewFile(@PathVariable("id") Long id) throws IOException {
        // 파일 정보를 DB에서 조회
        AllFiles file = allFilesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다."));

        // 실제 파일 경로
        Path filePath = Paths.get(file.getPath(), file.getFileName());
        Resource resource = new FileSystemResource(filePath.toFile());

        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 파일 타입을 설정 (Content-Type)
        String contentType = file.getFileType();
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // 텍스트 파일일 경우 내용을 UTF-8로 읽어 응답으로 반환
        if (contentType.equals("text/plain")) {
            String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);

            // HTTP 헤더 설정 (파일을 브라우저에서 UTF-8로 열도록)
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        }

        // 텍스트 파일이 아닌 경우, 브라우저에서 파일을 열 수 있도록 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20") + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }






    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable("id") Long id) {
        allFilesService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

