package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeptNotice is a Querydsl query type for DeptNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptNotice extends EntityPathBase<DeptNotice> {

    private static final long serialVersionUID = 2082882302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeptNotice deptNotice = new QDeptNotice("deptNotice");

    public final StringPath deptNoticeContent = createString("deptNoticeContent");

    public final NumberPath<Integer> deptNoticeNumber = createNumber("deptNoticeNumber", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> deptNoticeRegDate = createDateTime("deptNoticeRegDate", java.time.LocalDateTime.class);

    public final StringPath deptNoticeTitle = createString("deptNoticeTitle");

    public final QMember member;

    public QDeptNotice(String variable) {
        this(DeptNotice.class, forVariable(variable), INITS);
    }

    public QDeptNotice(Path<? extends DeptNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeptNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeptNotice(PathMetadata metadata, PathInits inits) {
        this(DeptNotice.class, metadata, inits);
    }

    public QDeptNotice(Class<? extends DeptNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

