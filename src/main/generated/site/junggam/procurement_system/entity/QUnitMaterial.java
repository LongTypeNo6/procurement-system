package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnitMaterial is a Querydsl query type for UnitMaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnitMaterial extends EntityPathBase<UnitMaterial> {

    private static final long serialVersionUID = -808727540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUnitMaterial unitMaterial = new QUnitMaterial("unitMaterial");

    public final QMaterial material;

    public final QUnit unit;

    public final QUnitMaterialId unitMaterialId;

    public QUnitMaterial(String variable) {
        this(UnitMaterial.class, forVariable(variable), INITS);
    }

    public QUnitMaterial(Path<? extends UnitMaterial> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUnitMaterial(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUnitMaterial(PathMetadata metadata, PathInits inits) {
        this(UnitMaterial.class, metadata, inits);
    }

    public QUnitMaterial(Class<? extends UnitMaterial> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit")) : null;
        this.unitMaterialId = inits.isInitialized("unitMaterialId") ? new QUnitMaterialId(forProperty("unitMaterialId")) : null;
    }

}

