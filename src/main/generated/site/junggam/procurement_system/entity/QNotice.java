package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = -1835677479L;

    public static final QNotice notice = new QNotice("notice");

    public final StringPath noticeContent = createString("noticeContent");

    public final NumberPath<Integer> noticeNumber = createNumber("noticeNumber", Integer.class);

    public final DatePath<java.time.LocalDate> noticeRegDate = createDate("noticeRegDate", java.time.LocalDate.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public final DatePath<java.time.LocalDate> noticeUpdateDate = createDate("noticeUpdateDate", java.time.LocalDate.class);

    public final StringPath noticeWriter = createString("noticeWriter");

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

