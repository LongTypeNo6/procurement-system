package site.junggam.procurement_system.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductUnitId implements Serializable {
    private String productCode;
    private String unitCode;
}
