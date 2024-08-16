package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.FileDTO;
import site.junggam.procurement_system.service.FileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkFileExists(@RequestParam("fileName") String fileName) {
        boolean exists = fileService.fileExists(fileName);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile[] files ,@RequestParam("overwrite") boolean overwrite) {
        List<String> fileNames = fileService.saveFiles(files,overwrite);
        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileDTO>> getFileList() {
        List<FileDTO> fileList = fileService.getFileList();
        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id")Long id) throws MalformedURLException {
        Path filePath = fileService.getFilePath(id);
        Resource resource = new UrlResource(filePath.toUri());

        // 한글 파일명 처리
        String encodedFileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                .body(resource);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable("id") Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }
}
