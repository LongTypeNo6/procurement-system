package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductUnitId is a Querydsl query type for ProductUnitId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductUnitId extends BeanPath<ProductUnitId> {

    private static final long serialVersionUID = -1214753171L;

    public static final QProductUnitId productUnitId = new QProductUnitId("productUnitId");

    public final StringPath productCode = createString("productCode");

    public final StringPath unitCode = createString("unitCode");

    public QProductUnitId(String variable) {
        super(ProductUnitId.class, forVariable(variable));
    }

    public QProductUnitId(Path<? extends ProductUnitId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductUnitId(PathMetadata metadata) {
        super(ProductUnitId.class, metadata);
    }

}

