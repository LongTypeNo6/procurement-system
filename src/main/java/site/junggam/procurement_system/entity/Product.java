package site.junggam.procurement_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_product")
public class Product {
    @Id
    private String productCode;
    private String productName;
    private Double productPrice;
    private String productStand;
    private String productTexture;
    private String productDrawFile;
    private String productEtcFile ;
    private LocalDateTime productRegDate;
    private LocalDateTime productModDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductUnit> productUnits = new HashSet<>();
    //@OrderBy("unitCode ASC") // 선택적: unitCode를 기준으로 정렬
    //private List<ProductUnit> productUnits = new ArrayList<>();


    public void addProductUnit(ProductUnit productUnit) {
        productUnits.add(productUnit);
        productUnit.setProduct(this);
    }

    public void removeProductUnit(ProductUnit productUnit) {
        productUnits.remove(productUnit);
        productUnit.setProduct(null);
    }

}
