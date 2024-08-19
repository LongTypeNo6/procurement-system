package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"procurementPlan"})
@Getter
@Table(name="tbl_release")
public class Release {
    @Id
    private String releaseCode;

    private String releaseRequestDept ; //출고요청을 한 부서
    @Builder.Default
    private LocalDateTime releaseRequestDate=LocalDateTime.now() ; //출고요청한날
    private LocalDateTime releaseDesireDate ; //출고희망일
    private Integer releaseDesireQuantity ; //출고희망양
    private String releaseRequestMemo; //출고요청비고
    private LocalDateTime releaseDate ; //실출고일
    @Builder.Default
    private Integer releaseQuantity=0 ; //실출고양
    private String releaseMemo; //출고비고

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReleaseStaus releaseStaus = ReleaseStaus.PENDING;

    @OneToOne
    @JoinColumn(name = "material_code")
    private TemMaterial temMaterial;
}
