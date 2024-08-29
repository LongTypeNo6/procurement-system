package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnit is a Querydsl query type for Unit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnit extends EntityPathBase<Unit> {

    private static final long serialVersionUID = 1861982149L;

    public static final QUnit unit = new QUnit("unit");

    public final StringPath unitCode = createString("unitCode");

    public final StringPath unitDrawFile = createString("unitDrawFile");

    public final StringPath unitEtcFile = createString("unitEtcFile");

    public final SetPath<UnitMaterial, QUnitMaterial> unitMaterials = this.<UnitMaterial, QUnitMaterial>createSet("unitMaterials", UnitMaterial.class, QUnitMaterial.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> unitModDate = createDateTime("unitModDate", java.time.LocalDateTime.class);

    public final StringPath unitName = createString("unitName");

    public final DateTimePath<java.time.LocalDateTime> unitRegDate = createDateTime("unitRegDate", java.time.LocalDateTime.class);

    public final StringPath unitStand = createString("unitStand");

    public final StringPath unitTexture = createString("unitTexture");

    public QUnit(String variable) {
        super(Unit.class, forVariable(variable));
    }

    public QUnit(Path<? extends Unit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnit(PathMetadata metadata) {
        super(Unit.class, metadata);
    }

}

