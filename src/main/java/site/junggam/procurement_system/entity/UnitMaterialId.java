package site.junggam.procurement_system.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UnitMaterialId implements Serializable {
    private String unitCode;
    private String materialCode;
}
