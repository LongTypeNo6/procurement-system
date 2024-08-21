package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitBomDTO {

    private Long id;
    private String unitName;
    private String unitCode;
    private String materialName;
    private String materialCode;
    private int unitBomQuantity;
    private String unitBomProcess;
}
