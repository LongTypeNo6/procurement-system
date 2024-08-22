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
@Setter
@ToString(exclude = {"contract", "inventory", "estimate"})
@Table(name = "tbl_material")
public class Material {

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

    @OneToOne(mappedBy = "material", fetch = FetchType.LAZY)
    private Contract contract;

    @OneToOne(mappedBy = "material", fetch = FetchType.LAZY)
    private Estimate estimate;

    @OneToOne(mappedBy = "material", fetch = FetchType.LAZY)
    private Inventory inventory;
}
