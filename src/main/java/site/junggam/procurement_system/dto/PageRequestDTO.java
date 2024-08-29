package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;

    //CYH : 24.08.29 날짜 항목 추가
    private LocalDate startDate1; //검색 시작설정날짜1
    private LocalDate endDate1; //검색 종료설정날짜1
    private LocalDate startDate2; //검색 시작설정날짜2
    private LocalDate endDate2; //검색 종료설정날짜2

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1, size, sort);
    }







}
