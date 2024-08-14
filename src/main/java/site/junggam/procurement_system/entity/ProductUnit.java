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
@Table(name = "tbl_product_unit")
public class ProductUnit {
    @EmbeddedId
    private ProductUnitId productUnitId;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne
    @MapsId("unitCode")
    @JoinColumn(name = "unit_code")
    private Unit unit;
}
