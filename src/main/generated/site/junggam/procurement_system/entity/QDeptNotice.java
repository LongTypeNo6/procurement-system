package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeptNotice is a Querydsl query type for DeptNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeptNotice extends EntityPathBase<DeptNotice> {

    private static final long serialVersionUID = 2082882302L;

    public static final QDeptNotice deptNotice = new QDeptNotice("deptNotice");

    public final StringPath deptNoticeContent = createString("deptNoticeContent");

    public final NumberPath<Integer> deptNoticeNumber = createNumber("deptNoticeNumber", Integer.class);

    public final DatePath<java.time.LocalDate> deptNoticeRegDate = createDate("deptNoticeRegDate", java.time.LocalDate.class);

    public final StringPath deptNoticeTitle = createString("deptNoticeTitle");

    public final DatePath<java.time.LocalDate> deptNoticeUpdateDate = createDate("deptNoticeUpdateDate", java.time.LocalDate.class);

    public final StringPath deptNoticeWriter = createString("deptNoticeWriter");

    public QDeptNotice(String variable) {
        super(DeptNotice.class, forVariable(variable));
    }

    public QDeptNotice(Path<? extends DeptNotice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeptNotice(PathMetadata metadata) {
        super(DeptNotice.class, metadata);
    }

}

