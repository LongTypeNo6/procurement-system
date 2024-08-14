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
public class ProductDTO {
    private String productCode;
    private String productName;
    private Double productPrice;
    private String productStand;
    private String productTexture;
    private String productDrawFile;
    private String productEtcFile ;
    private LocalDateTime productRegDate;
    private LocalDateTime productModDate;
    private Set<ProductUnitDTO> productUnits;

}
