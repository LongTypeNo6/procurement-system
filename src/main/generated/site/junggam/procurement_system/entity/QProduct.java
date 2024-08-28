package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 784718606L;

    public static final QProduct product = new QProduct("product");

    public final StringPath productCode = createString("productCode");

    public final StringPath productDrawFile = createString("productDrawFile");

    public final StringPath productEtcFile = createString("productEtcFile");

    public final DateTimePath<java.time.LocalDateTime> productModDate = createDateTime("productModDate", java.time.LocalDateTime.class);

    public final StringPath productName = createString("productName");

    public final NumberPath<Double> productPrice = createNumber("productPrice", Double.class);

    public final DateTimePath<java.time.LocalDateTime> productRegDate = createDateTime("productRegDate", java.time.LocalDateTime.class);

    public final StringPath productStand = createString("productStand");

    public final StringPath productTexture = createString("productTexture");

    public final SetPath<ProductUnit, QProductUnit> productUnits = this.<ProductUnit, QProductUnit>createSet("productUnits", ProductUnit.class, QProductUnit.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

