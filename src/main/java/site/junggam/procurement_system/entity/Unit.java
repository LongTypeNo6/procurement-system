package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "material_code")
    private Material material;

}
