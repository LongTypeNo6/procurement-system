package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_unit_material")
public class UnitMaterial {
    @EmbeddedId
    private UnitMaterialId unitMaterialId;

    @ManyToOne
    @MapsId("unitCode")
    @JoinColumn(name = "unit_code")
    private Unit unit;

    @ManyToOne
    @MapsId("materialCode")
    @JoinColumn(name = "material_code")
    private Material material;
}
