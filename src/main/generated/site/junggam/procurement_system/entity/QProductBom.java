package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductBom is a Querydsl query type for ProductBom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBom extends EntityPathBase<ProductBom> {

    private static final long serialVersionUID = 45066194L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductBom productBom = new QProductBom("productBom");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public final StringPath productBomProcess = createString("productBomProcess");

    public final NumberPath<Integer> productBomQuantity = createNumber("productBomQuantity", Integer.class);

    public final QUnit unit;

    public QProductBom(String variable) {
        this(ProductBom.class, forVariable(variable), INITS);
    }

    public QProductBom(Path<? extends ProductBom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductBom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductBom(PathMetadata metadata, PathInits inits) {
        this(ProductBom.class, metadata, inits);
    }

    public QProductBom(Class<? extends ProductBom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit")) : null;
    }

}

