package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEstimate is a Querydsl query type for Estimate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEstimate extends EntityPathBase<Estimate> {

    private static final long serialVersionUID = 1895422697L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstimate estimate = new QEstimate("estimate");

    public final QContract contract;

    public final StringPath estimateCode = createString("estimateCode");

    public final StringPath estimateFile = createString("estimateFile");

    public final StringPath estimateMemo = createString("estimateMemo");

    public final EnumPath<EstimateStatus> estimateStatus = createEnum("estimateStatus", EstimateStatus.class);

    public final QMaterial material;

    public final QPurchaser purchaser;

    public QEstimate(String variable) {
        this(Estimate.class, forVariable(variable), INITS);
    }

    public QEstimate(Path<? extends Estimate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEstimate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEstimate(PathMetadata metadata, PathInits inits) {
        this(Estimate.class, metadata, inits);
    }

    public QEstimate(Class<? extends Estimate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.purchaser = inits.isInitialized("purchaser") ? new QPurchaser(forProperty("purchaser")) : null;
    }

}

