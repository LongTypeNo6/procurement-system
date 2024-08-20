package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBomDTO {
    private Long id;
    private String productName;
    private String productCode;
    private int productBomQuantity;
    private String productBomProcess;
    private List<UnitBomDTO> unitBomDTOList;
}
