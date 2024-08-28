package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPurchaser is a Querydsl query type for Purchaser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaser extends EntityPathBase<Purchaser> {

    private static final long serialVersionUID = 1755618800L;

    public static final QPurchaser purchaser = new QPurchaser("purchaser");

    public final StringPath purchaserAddress = createString("purchaserAddress");

    public final StringPath purchaserCategory = createString("purchaserCategory");

    public final StringPath purchaserCode = createString("purchaserCode");

    public final StringPath purchaserEmail = createString("purchaserEmail");

    public final StringPath purchaserFax = createString("purchaserFax");

    public final StringPath purchaserManager = createString("purchaserManager");

    public final StringPath purchaserManagerEmail = createString("purchaserManagerEmail");

    public final StringPath purchaserManagerFax = createString("purchaserManagerFax");

    public final StringPath purchaserManagerTel = createString("purchaserManagerTel");

    public final StringPath purchaserMemo = createString("purchaserMemo");

    public final StringPath purchaserName = createString("purchaserName");

    public final StringPath purchaserPresident = createString("purchaserPresident");

    public final StringPath purchaserTel = createString("purchaserTel");

    public final StringPath purchaserType = createString("purchaserType");

    public QPurchaser(String variable) {
        super(Purchaser.class, forVariable(variable));
    }

    public QPurchaser(Path<? extends Purchaser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchaser(PathMetadata metadata) {
        super(Purchaser.class, metadata);
    }

}

