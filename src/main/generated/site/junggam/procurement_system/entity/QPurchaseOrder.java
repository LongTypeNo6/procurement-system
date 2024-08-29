package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseOrder is a Querydsl query type for PurchaseOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseOrder extends EntityPathBase<PurchaseOrder> {

    private static final long serialVersionUID = 646727084L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseOrder purchaseOrder = new QPurchaseOrder("purchaseOrder");

    public final QProcurementPlan procurementPlan;

    public final StringPath purchaseOrderCode = createString("purchaseOrderCode");

    public final DateTimePath<java.time.LocalDateTime> purchaseOrderDate = createDateTime("purchaseOrderDate", java.time.LocalDateTime.class);

    public final StringPath purchaseOrderMemo = createString("purchaseOrderMemo");

    public final EnumPath<PurchaseOrderStatus> purchaseOrderStatus = createEnum("purchaseOrderStatus", PurchaseOrderStatus.class);

    public final QWarehousing warehousing;

    public QPurchaseOrder(String variable) {
        this(PurchaseOrder.class, forVariable(variable), INITS);
    }

    public QPurchaseOrder(Path<? extends PurchaseOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseOrder(PathMetadata metadata, PathInits inits) {
        this(PurchaseOrder.class, metadata, inits);
    }

    public QPurchaseOrder(Class<? extends PurchaseOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.procurementPlan = inits.isInitialized("procurementPlan") ? new QProcurementPlan(forProperty("procurementPlan"), inits.get("procurementPlan")) : null;
        this.warehousing = inits.isInitialized("warehousing") ? new QWarehousing(forProperty("warehousing"), inits.get("warehousing")) : null;
    }

}

