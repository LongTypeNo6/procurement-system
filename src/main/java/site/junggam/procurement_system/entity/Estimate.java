package site.junggam.procurement_system.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"material", "purchaser","contract"})
@Getter
@Table(name="tbl_estimate")
public class Estimate {

    @Id
    String estimateCode;
    String estimateFile;
    String estimateMemo;

    @Builder.Default
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaserCode")
    private Purchaser purchaser=null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materialCode")
    private Material material;

    @Builder.Default
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractCode")
    private Contract contract=null;

    @Builder.Default
    private EstimateStatus estimateStatus= EstimateStatus.NOT_SELECTED;
}
