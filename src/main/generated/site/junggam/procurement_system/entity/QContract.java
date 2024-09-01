package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -1006713133L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final StringPath contractCode = createString("contractCode");

    public final DateTimePath<java.time.LocalDateTime> contractDate = createDateTime("contractDate", java.time.LocalDateTime.class);

    public final StringPath contractFile = createString("contractFile");

    public final NumberPath<Integer> contractLeadTime = createNumber("contractLeadTime", Integer.class);

    public final StringPath contractMemo = createString("contractMemo");

    public final NumberPath<Double> contractPrice = createNumber("contractPrice", Double.class);

    public final QMaterial material;

    public final QPurchaser purchaser;

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.purchaser = inits.isInitialized("purchaser") ? new QPurchaser(forProperty("purchaser")) : null;
    }

}

