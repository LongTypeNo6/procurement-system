package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"material", "purchaser"})
@Getter
@Table(name="tbl_contract")
public class Contract {

    @Id
    private String contractCode;
    private String contractFile;
    private Double contractPrice;
    private Integer contractLeadTime;
    private String contractMemo;

    @OneToOne
    @JoinColumn(name = "material_code")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaserCode")
    private Purchaser purchaser;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ContractStatus contractStatus=ContractStatus.PENDING;
}
