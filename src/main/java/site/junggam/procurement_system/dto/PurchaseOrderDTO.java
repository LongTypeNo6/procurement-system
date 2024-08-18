package site.junggam.procurement_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private String purchaseOrderCode; // 발주 코드
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime purchaseOrderDate; // 발주 일자
    private String purchaseOrderMemo; // 발주 메모

    // 필요한 필드만 포함
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime procurementPlanDeadLine; // 조달 계획 마감일
    private int procurementPlanQuantity; // 조달 계획 수량, Integer로 하면 널값에 대해서 처리해야하므로 기본값으로작성
    private String materialName; // 자재 이름
    private String materialStand; // 자재 규격
    private String materialTexture; // 자재 재질
    private double contractPrice; // 계약 금액
    private String purchaserCode; //거래처코드(사업자등록번호)
    private String purchaserName; // 거래처 이름
    private String purchaserManager; // 거래처 담당자
    private String purchaserManagerTel; // 거래처 담당자 전화번호
    private String purchaserManagerEmail; // 거래처 담당자 이메일
    private String purchaserManagerFax; // 거래처 담당자 팩스
    private int inspectionPlanCount; //검수계획 개수


    @Builder.Default
    private PurchaseOrderStatus purchaseOrderStatus = PurchaseOrderStatus.PENDING; // 발주 상태 기본값
}
