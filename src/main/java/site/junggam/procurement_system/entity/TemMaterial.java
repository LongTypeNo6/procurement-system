package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "contract")
public class TemMaterial {
    //임시 엔티티
    @Id
    private String materialCode;
    private String materialName;
    private String materialStand;
    private String materialTexture;
    private String materialDrawFile;
    private String materialEtcFile;
    private LocalDateTime materialRegDate;
    private LocalDateTime materialModDate;
    private Integer materialSafeQuantity;

    @OneToOne(mappedBy = "temMaterial", fetch = FetchType.LAZY)
    private Contract contract;
    //아예 재고엔티티 추가 하고 연결
    @OneToOne(mappedBy = "temMaterial", fetch = FetchType.LAZY)
    private Inventory inventory;
}
