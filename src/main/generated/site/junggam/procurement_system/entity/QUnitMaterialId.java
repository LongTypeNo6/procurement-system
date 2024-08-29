package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUnitMaterialId is a Querydsl query type for UnitMaterialId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUnitMaterialId extends BeanPath<UnitMaterialId> {

    private static final long serialVersionUID = 201916999L;

    public static final QUnitMaterialId unitMaterialId = new QUnitMaterialId("unitMaterialId");

    public final StringPath materialCode = createString("materialCode");

    public final StringPath unitCode = createString("unitCode");

    public QUnitMaterialId(String variable) {
        super(UnitMaterialId.class, forVariable(variable));
    }

    public QUnitMaterialId(Path<? extends UnitMaterialId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnitMaterialId(PathMetadata metadata) {
        super(UnitMaterialId.class, metadata);
    }

}

