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
public class UnitMaterialId implements Serializable {
    private String unitCode;
    private String materialCode;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UnitMaterialId that = (UnitMaterialId) o;
//        return Objects.equals(unitCode, that.unitCode) &&
//                Objects.equals(materialCode, that.materialCode);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(unitCode, materialCode);
//    }

}
