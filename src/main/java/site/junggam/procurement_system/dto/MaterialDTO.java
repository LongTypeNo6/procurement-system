package site.junggam.procurement_system.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.MaterialContractStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO {
    private String materialCode;
    private String materialName;
    private String materialStand;
    private String materialTexture;
    private String materialDrawFile;
    private String materialEtcFile;
    private LocalDateTime materialRegDate;
    private LocalDateTime materialModDate;
    private Integer materialSafeQuantity;

    private InventoryDTO inventoryDTO;

    @Builder.Default
    private List<ProcurementPlanDTO> procurementPlanDTOList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MaterialContractStatus materialContractStatus=MaterialContractStatus.PENDING;
}
