package site.junggam.procurement_system.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.ProcurementPlan;
import site.junggam.procurement_system.entity.ReleaseStaus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDTO {

    private String releaseCode;
    private String releaseRequestDept ; //출고요청을 한 부서
    @Builder.Default
    private LocalDateTime releaseRequestDate=LocalDateTime.now() ; //출고요청한날
    private LocalDateTime releaseDesireDate ; //출고희망일
    private int releaseDesireQuantity; //출고희망양
    private String releaseRequestMemo; //출고요청비고
    private LocalDateTime releaseDate ; //실출고일
    @Builder.Default
    private int releaseQuantity=0 ; //실출고양
    private String releaseMemo; //출고비고

    private LocalDateTime procurementPlanDeadLine; // 조달 계획 마감일
    private int procurementPlanQuantity;
    private String materialCode; //자재코드
    private String materialName; // 자재 이름
    private String materialStand; // 자재 규격
    private String materialTexture; // 자재 재질
    private String purchaserCode; //거래처코드(사업자등록번호)
    private String purchaserName; // 거래처 이름
    private int availableQuantity; //가용재고
    private int materialQuantity; //실재고수량

    @Builder.Default
    private ReleaseStaus releaseStaus = ReleaseStaus.PENDING;
}
