package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRelease is a Querydsl query type for Release
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRelease extends EntityPathBase<Release> {

    private static final long serialVersionUID = -2110179802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRelease release = new QRelease("release");

    public final QMaterial material;

    public final StringPath releaseCode = createString("releaseCode");

    public final DateTimePath<java.time.LocalDateTime> releaseDate = createDateTime("releaseDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> releaseDesireDate = createDateTime("releaseDesireDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> releaseDesireQuantity = createNumber("releaseDesireQuantity", Integer.class);

    public final StringPath releaseMemo = createString("releaseMemo");

    public final NumberPath<Integer> releaseQuantity = createNumber("releaseQuantity", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> releaseRequestDate = createDateTime("releaseRequestDate", java.time.LocalDateTime.class);

    public final StringPath releaseRequestDept = createString("releaseRequestDept");

    public final StringPath releaseRequestMemo = createString("releaseRequestMemo");

    public final EnumPath<ReleaseStaus> releaseStaus = createEnum("releaseStaus", ReleaseStaus.class);

    public QRelease(String variable) {
        this(Release.class, forVariable(variable), INITS);
    }

    public QRelease(Path<? extends Release> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRelease(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRelease(PathMetadata metadata, PathInits inits) {
        this(Release.class, metadata, inits);
    }

    public QRelease(Class<? extends Release> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.material = inits.isInitialized("material") ? new QMaterial(forProperty("material"), inits.get("material")) : null;
    }

}

