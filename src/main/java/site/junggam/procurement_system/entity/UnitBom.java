package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"unit","material"})
@Table(name="tbl_unit_bom")
public class UnitBom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_code")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "material_code")
    private Material material;

    private int unitBomQuantity;
    private String unitBomProcess;
}
