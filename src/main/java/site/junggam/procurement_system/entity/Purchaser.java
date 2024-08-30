package site.junggam.procurement_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name="tbl_purchaser")
public class Purchaser {

    @Id
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
}
