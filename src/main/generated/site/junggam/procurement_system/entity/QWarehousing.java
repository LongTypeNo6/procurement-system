package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWarehousing is a Querydsl query type for Warehousing
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWarehousing extends EntityPathBase<Warehousing> {

    private static final long serialVersionUID = -1237214881L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWarehousing warehousing = new QWarehousing("warehousing");

    public final QPurchaseOrder purchaseOrder;

    public final StringPath warehousingCode = createString("warehousingCode");

    public final EnumPath<WarehousingStatus> warehousingStatus = createEnum("warehousingStatus", WarehousingStatus.class);

    public QWarehousing(String variable) {
        this(Warehousing.class, forVariable(variable), INITS);
    }

    public QWarehousing(Path<? extends Warehousing> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWarehousing(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWarehousing(PathMetadata metadata, PathInits inits) {
        this(Warehousing.class, metadata, inits);
    }

    public QWarehousing(Class<? extends Warehousing> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.purchaseOrder = inits.isInitialized("purchaseOrder") ? new QPurchaseOrder(forProperty("purchaseOrder"), inits.get("purchaseOrder")) : null;
    }

}

