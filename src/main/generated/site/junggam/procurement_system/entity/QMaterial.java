package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMaterial is a Querydsl query type for Material
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMaterial extends EntityPathBase<Material> {

    private static final long serialVersionUID = -140698904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaterial material = new QMaterial("material");

    public final QContract contract;

    public final QEstimate estimate;

    public final QInventory inventory;

    public final StringPath materialCode = createString("materialCode");

    public final EnumPath<MaterialContractStatus> materialContractStatus = createEnum("materialContractStatus", MaterialContractStatus.class);

    public final StringPath materialDrawFile = createString("materialDrawFile");

    public final StringPath materialEtcFile = createString("materialEtcFile");

    public final DateTimePath<java.time.LocalDateTime> materialModDate = createDateTime("materialModDate", java.time.LocalDateTime.class);

    public final StringPath materialName = createString("materialName");

    public final DateTimePath<java.time.LocalDateTime> materialRegDate = createDateTime("materialRegDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> materialSafeQuantity = createNumber("materialSafeQuantity", Integer.class);

    public final StringPath materialStand = createString("materialStand");

    public final StringPath materialTexture = createString("materialTexture");

    public QMaterial(String variable) {
        this(Material.class, forVariable(variable), INITS);
    }

    public QMaterial(Path<? extends Material> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMaterial(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMaterial(PathMetadata metadata, PathInits inits) {
        this(Material.class, metadata, inits);
    }

    public QMaterial(Class<? extends Material> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
        this.estimate = inits.isInitialized("estimate") ? new QEstimate(forProperty("estimate"), inits.get("estimate")) : null;
        this.inventory = inits.isInitialized("inventory") ? new QInventory(forProperty("inventory"), inits.get("inventory")) : null;
    }

}

