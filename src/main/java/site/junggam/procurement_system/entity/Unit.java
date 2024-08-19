package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_unit")
public class Unit {
    @Id
    private String unitCode;
    private String unitName;
    private String unitStand;
    private String unitTexture;
    private String unitDrawFile ;
    private String unitEtcFile;
    private LocalDateTime unitRegDate;
    private LocalDateTime unitModDate;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UnitMaterial> unitMaterials = new HashSet<>();
}
