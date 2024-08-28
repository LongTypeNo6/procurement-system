package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnitBom is a Querydsl query type for UnitBom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnitBom extends EntityPathBase<UnitBom> {

    private static final long serialVersionUID = 807639995L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUnitBom unitBom = new QUnitBom("unitBom");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMaterial material;

    public final QUnit unit;

    public final StringPath unitBomProcess = createString("unitBomProcess");

    public final NumberPath<Integer> unitBomQuantity = createNumber("unitBomQuantity", Integer.class);

    public QUnitBom(String variable) {
        this(UnitBom.class, forVariable(variable), INITS);
    }

    public QUnitBom(Path<? extends UnitBom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUnitBom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUnitBom(PathMetadata metadata, PathInits inits) {
        this(UnitBom.class, metadata, inits);
    }

    public QUnitBom(Class<? extends UnitBom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit")) : null;
    }

}

