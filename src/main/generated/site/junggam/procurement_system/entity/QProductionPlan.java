package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductionPlan is a Querydsl query type for ProductionPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductionPlan extends EntityPathBase<ProductionPlan> {

    private static final long serialVersionUID = 1599769731L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductionPlan productionPlan = new QProductionPlan("productionPlan");

    public final QProduct product;

    public final StringPath productionPlanCode = createString("productionPlanCode");

    public final DateTimePath<java.time.LocalDateTime> productionPlanDate = createDateTime("productionPlanDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> productionPlanDeadLine = createDateTime("productionPlanDeadLine", java.time.LocalDateTime.class);

    public final NumberPath<Integer> productionPlanQuantity = createNumber("productionPlanQuantity", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> productionPlanRegDate = createDateTime("productionPlanRegDate", java.time.LocalDateTime.class);

    public final QUnit unit;

    public QProductionPlan(String variable) {
        this(ProductionPlan.class, forVariable(variable), INITS);
    }

    public QProductionPlan(Path<? extends ProductionPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductionPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductionPlan(PathMetadata metadata, PathInits inits) {
        this(ProductionPlan.class, metadata, inits);
    }

    public QProductionPlan(Class<? extends ProductionPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit")) : null;
    }

}

