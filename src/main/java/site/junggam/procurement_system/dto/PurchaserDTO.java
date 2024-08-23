package site.junggam.procurement_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaserDTO {
    private String purchaserCode;
    private String purchaserName ;
    private String purchaserAddress ;
    private String purchaserFax ;
    private String purchaserEmail ;
    private String purchaserType ;
    private String purchaserTel ;
    private String purchaserCategory ;
    private String purchaserPresident ;
    private String purchaserManager ;
    private String purchaserManagerTel ;
    private String purchaserManagerEmail ;
    private String purchaserManagerFax ;
    private String purchaserMemo;

    @Builder.Default
    private List<ContractDTO> contractDTOList = new ArrayList<>();
}
