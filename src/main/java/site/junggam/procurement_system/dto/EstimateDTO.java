package site.junggam.procurement_system.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.junggam.procurement_system.entity.EstimateStatus;
import site.junggam.procurement_system.entity.Material;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstimateDTO {
    String estimateCode;
    String estimateFile;
    String estimateMemo;

    @Builder.Default
    private String purchaserCode=null;
    @Builder.Default
    private String purchaserName=null;

    private Material materialDTO;

    @Builder.Default
    private String contractCode=null;

    @Builder.Default
    private EstimateStatus estimateStatus= EstimateStatus.NOT_SELECTED;
}
