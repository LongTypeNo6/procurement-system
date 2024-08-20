package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"unit","product"})
@Table(name="tbl_product_bom")
public class ProductBom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_code")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    private int productBomQuantity;
    private String productBomProcess;

}
