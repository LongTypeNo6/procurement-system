package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemMaterialDTO {

    private String materialCode;
    private String materialName;
    private String materialStand;
    private String materialTexture;
    private String materialDrawFile;
    private String materialEtcFile;
    private LocalDateTime materialRegDate;
    private LocalDateTime materialModDate;
    private Integer materialSafeQuantity;
    private ContractDTO contractDTO;

    @Builder.Default
    private List<ProcurementPlanDTO> procurementPlanDTOList = new ArrayList<>();

}
