package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWarehousingHistory is a Querydsl query type for WarehousingHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWarehousingHistory extends EntityPathBase<WarehousingHistory> {

    private static final long serialVersionUID = -1451726283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWarehousingHistory warehousingHistory = new QWarehousingHistory("warehousingHistory");

    public final QWarehousing warehousing;

    public final DateTimePath<java.time.LocalDateTime> warehousingDate = createDateTime("warehousingDate", java.time.LocalDateTime.class);

    public final StringPath warehousingHistoryCode = createString("warehousingHistoryCode");

    public final EnumPath<WarehousingHistoryStatus> warehousingHistoryStatus = createEnum("warehousingHistoryStatus", WarehousingHistoryStatus.class);

    public final NumberPath<Integer> warehousingQuantity = createNumber("warehousingQuantity", Integer.class);

    public final StringPath warehousingResultMemo = createString("warehousingResultMemo");

    public final NumberPath<Long> warehousingShipmentSpec = createNumber("warehousingShipmentSpec", Long.class);

    public final NumberPath<Long> warehousingSpec = createNumber("warehousingSpec", Long.class);

    public QWarehousingHistory(String variable) {
        this(WarehousingHistory.class, forVariable(variable), INITS);
    }

    public QWarehousingHistory(Path<? extends WarehousingHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWarehousingHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWarehousingHistory(PathMetadata metadata, PathInits inits) {
        this(WarehousingHistory.class, metadata, inits);
    }

    public QWarehousingHistory(Class<? extends WarehousingHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.warehousing = inits.isInitialized("warehousing") ? new QWarehousing(forProperty("warehousing"), inits.get("warehousing")) : null;
    }

}

