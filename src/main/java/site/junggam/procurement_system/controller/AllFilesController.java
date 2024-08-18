package site.junggam.procurement_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.junggam.procurement_system.dto.AllFilesDTO;
import site.junggam.procurement_system.service.AllFilesService;

import java.io.File;
import java.io.IOException;
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
        List<AllFilesDTO> files = allFilesService.getFilesByForeignCode(foreignCode);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable("id") Long id) {
        allFilesService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

