package com.dmz.api.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -760919634L;

    public static final QMember member = new QMember("member1");

    public final com.dmz.global.entity.QBaseTime _super = new com.dmz.global.entity.QBaseTime(this);

    public final ListPath<com.dmz.api.community.domain.Community, com.dmz.api.community.domain.QCommunity> communityList = this.<com.dmz.api.community.domain.Community, com.dmz.api.community.domain.QCommunity>createList("communityList", com.dmz.api.community.domain.Community.class, com.dmz.api.community.domain.QCommunity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profile = createString("profile");

    public final StringPath providerId = createString("providerId");

    public final ListPath<com.dmz.api.community.domain.Reply, com.dmz.api.community.domain.QReply> replyList = this.<com.dmz.api.community.domain.Reply, com.dmz.api.community.domain.QReply>createList("replyList", com.dmz.api.community.domain.Reply.class, com.dmz.api.community.domain.QReply.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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

