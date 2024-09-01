package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Builder.Default
    private LocalDateTime contractDate = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "material_code")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaserCode")
    private Purchaser purchaser;

    public void changeContractPrice(Double newPrice) {this.contractPrice=newPrice;}

    public void changeContractLeadTime(Integer newLeadTime) {this.contractLeadTime=newLeadTime;}

    public void changeContractMemo(String newMemo) {this.contractMemo=newMemo;}
}
