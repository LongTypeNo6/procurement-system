package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProcurementPlan is a Querydsl query type for ProcurementPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProcurementPlan extends EntityPathBase<ProcurementPlan> {

    private static final long serialVersionUID = -1257923304L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProcurementPlan procurementPlan = new QProcurementPlan("procurementPlan");

    public final QMaterial material;

    public final StringPath procurementPlanCode = createString("procurementPlanCode");

    public final DateTimePath<java.time.LocalDateTime> procurementPlanDeadLine = createDateTime("procurementPlanDeadLine", java.time.LocalDateTime.class);

    public final NumberPath<Integer> procurementPlanQuantity = createNumber("procurementPlanQuantity", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> procurementPlanRegDate = createDateTime("procurementPlanRegDate", java.time.LocalDateTime.class);

    public final EnumPath<ProcurementPlanStatus> procurementPlanStatus = createEnum("procurementPlanStatus", ProcurementPlanStatus.class);

    public final QProductionPlan productionPlan;

    public final QPurchaseOrder purchaseOrder;

    public QProcurementPlan(String variable) {
        this(ProcurementPlan.class, forVariable(variable), INITS);
    }

    public QProcurementPlan(Path<? extends ProcurementPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProcurementPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProcurementPlan(PathMetadata metadata, PathInits inits) {
        this(ProcurementPlan.class, metadata, inits);
    }

    public QProcurementPlan(Class<? extends ProcurementPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.productionPlan = inits.isInitialized("productionPlan") ? new QProductionPlan(forProperty("productionPlan"), inits.get("productionPlan")) : null;
        this.purchaseOrder = inits.isInitialized("purchaseOrder") ? new QPurchaseOrder(forProperty("purchaseOrder"), inits.get("purchaseOrder")) : null;
    }

}

