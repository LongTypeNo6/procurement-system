package site.junggam.procurement_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.junggam.procurement_system.entity.AllFiles;

@SpringBootTest
public class AllFilesRepositoryTest {

    @Autowired
    private AllFilesRepository repository;
    
//    @Test
//    public void test() {
//        AllFiles allFiles = AllFiles.builder()
//                .fileName("파일이름")
//                .path("경로")
//                .build();
//        repository.save(allFiles);
//    }
}
