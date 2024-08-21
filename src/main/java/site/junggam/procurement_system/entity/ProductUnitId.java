package site.junggam.procurement_system.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class ProductUnitId implements Serializable {
    private String productCode;
    private String unitCode;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductUnitId that = (ProductUnitId) o;
//        return Objects.equals(productCode, that.productCode) &&
//                Objects.equals(unitCode, that.unitCode);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productCode, unitCode);
//    }

}
