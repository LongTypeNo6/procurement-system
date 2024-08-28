package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductUnit is a Querydsl query type for ProductUnit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductUnit extends EntityPathBase<ProductUnit> {

    private static final long serialVersionUID = 1397617074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductUnit productUnit = new QProductUnit("productUnit");

    public final QProduct product;

    public final QProductUnitId productUnitId;

    public final QUnit unit;

    public QProductUnit(String variable) {
        this(ProductUnit.class, forVariable(variable), INITS);
    }

    public QProductUnit(Path<? extends ProductUnit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductUnit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductUnit(PathMetadata metadata, PathInits inits) {
        this(ProductUnit.class, metadata, inits);
    }

    public QProductUnit(Class<? extends ProductUnit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.productUnitId = inits.isInitialized("productUnitId") ? new QProductUnitId(forProperty("productUnitId")) : null;
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit")) : null;
    }

}

