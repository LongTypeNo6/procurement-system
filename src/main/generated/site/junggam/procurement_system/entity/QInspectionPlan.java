package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInspectionPlan is a Querydsl query type for InspectionPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInspectionPlan extends EntityPathBase<InspectionPlan> {

    private static final long serialVersionUID = 1294324670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInspectionPlan inspectionPlan = new QInspectionPlan("inspectionPlan");

    public final StringPath inspectionPlanCode = createString("inspectionPlanCode");

    public final StringPath inspectionPlanComplementary = createString("inspectionPlanComplementary");

    public final DateTimePath<java.time.LocalDateTime> inspectionPlanDateTime = createDateTime("inspectionPlanDateTime", java.time.LocalDateTime.class);

    public final EnumPath<InspectionPlanDeliveryProgress> inspectionPlanDeliveryProgress = createEnum("inspectionPlanDeliveryProgress", InspectionPlanDeliveryProgress.class);

    public final StringPath inspectionPlanMemo = createString("inspectionPlanMemo");

    public final NumberPath<Integer> inspectionPlanProgress = createNumber("inspectionPlanProgress", Integer.class);

    public final EnumPath<InspectionPlanStatus> inspectionPlanStatus = createEnum("inspectionPlanStatus", InspectionPlanStatus.class);

    public final DateTimePath<java.time.LocalDateTime> inspectionResultDateTime = createDateTime("inspectionResultDateTime", java.time.LocalDateTime.class);

    public final QPurchaseOrder purchaseOrder;

    public QInspectionPlan(String variable) {
        this(InspectionPlan.class, forVariable(variable), INITS);
    }

    public QInspectionPlan(Path<? extends InspectionPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInspectionPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInspectionPlan(PathMetadata metadata, PathInits inits) {
        this(InspectionPlan.class, metadata, inits);
    }

    public QInspectionPlan(Class<? extends InspectionPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.purchaseOrder = inits.isInitialized("purchaseOrder") ? new QPurchaseOrder(forProperty("purchaseOrder"), inits.get("purchaseOrder")) : null;
    }

}

