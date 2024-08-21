package site.junggam.procurement_system.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.dto.PageRequestDTO;
import site.junggam.procurement_system.dto.PageResultDTO;
import site.junggam.procurement_system.entity.Purchaser;
import site.junggam.procurement_system.repository.PurchaserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AllFilesServiceTest {

    @Autowired
    PurchaserRepository purchaserRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private String setUploadDir(String subDirectory) {
        String folderPath = subDirectory.replace("//", File.separator);
        File uploadPathFolder = new File(uploadDir, folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return uploadPathFolder.getAbsolutePath();  // 절대 경로 반환
    }

    @Test
    public void uploadFile() {
        List<Purchaser> list=purchaserRepository.findAll();
        List<String> listName = new ArrayList<String>();
        for(Purchaser purchaser:list) {
            listName.add(purchaser.getPurchaserCode());
        }
        for(String name:listName) {
            File file = new File(setUploadDir(name));
        }
    }
}
