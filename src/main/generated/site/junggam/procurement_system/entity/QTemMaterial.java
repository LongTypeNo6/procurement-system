package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemMaterial is a Querydsl query type for TemMaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemMaterial extends EntityPathBase<TemMaterial> {

    private static final long serialVersionUID = -1147038718L;

    public static final QTemMaterial temMaterial = new QTemMaterial("temMaterial");

    public final StringPath materialCode = createString("materialCode");

    public final StringPath materialDrawFile = createString("materialDrawFile");

    public final StringPath materialEtcFile = createString("materialEtcFile");

    public final DateTimePath<java.time.LocalDateTime> materialModDate = createDateTime("materialModDate", java.time.LocalDateTime.class);

    public final StringPath materialName = createString("materialName");

    public final DateTimePath<java.time.LocalDateTime> materialRegDate = createDateTime("materialRegDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> materialSafeQuantity = createNumber("materialSafeQuantity", Integer.class);

    public final StringPath materialStand = createString("materialStand");

    public final StringPath materialTexture = createString("materialTexture");

    public QTemMaterial(String variable) {
        super(TemMaterial.class, forVariable(variable));
    }

    public QTemMaterial(Path<? extends TemMaterial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemMaterial(PathMetadata metadata) {
        super(TemMaterial.class, metadata);
    }

}

