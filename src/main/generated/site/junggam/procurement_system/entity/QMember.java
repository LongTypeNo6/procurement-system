package site.junggam.procurement_system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1873757029L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> memberBirth = createDateTime("memberBirth", java.time.LocalDateTime.class);

    public final StringPath memberDept = createString("memberDept");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberId = createString("memberId");

    public final StringPath memberImageFile = createString("memberImageFile");

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Integer> memberNumber = createNumber("memberNumber", Integer.class);

    public final StringPath memberPw = createString("memberPw");

    public final StringPath memberRank = createString("memberRank");

    public final EnumPath<MemberStatus> memberStatus = createEnum("memberStatus", MemberStatus.class);

    public final StringPath memberTeam = createString("memberTeam");

    public final StringPath memberTel = createString("memberTel");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

