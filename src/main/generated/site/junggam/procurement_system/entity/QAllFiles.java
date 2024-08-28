package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAllFiles is a Querydsl query type for AllFiles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllFiles extends EntityPathBase<AllFiles> {

    private static final long serialVersionUID = 1335091991L;

    public static final QAllFiles allFiles = new QAllFiles("allFiles");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileType = createString("fileType");

    public final StringPath foreignCode = createString("foreignCode");

    public final NumberPath<Long> inum = createNumber("inum", Long.class);

    public final StringPath path = createString("path");

    public QAllFiles(String variable) {
        super(AllFiles.class, forVariable(variable));
    }

    public QAllFiles(Path<? extends AllFiles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllFiles(PathMetadata metadata) {
        super(AllFiles.class, metadata);
    }

}

