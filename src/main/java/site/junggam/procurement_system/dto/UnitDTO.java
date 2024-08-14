package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO {
    private String unitCode;
    private String unitName;
    private String unitStand;
    private String unitTexture;
    private String unitDrawFile;
    private String unitEtcFile;
    private LocalDateTime unitRegDate;
    private LocalDateTime unitModDate;

    private Set<UnitMaterialDTO> unitMaterials;

}
