package com.dmz.api.community.dto.response.detail;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dmz.api.community.dto.response.detail.QReplyResponse is a Querydsl Projection type for ReplyResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReplyResponse extends ConstructorExpression<ReplyResponse> {

    private static final long serialVersionUID = 1151827113L;

    public QReplyResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> profile, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Boolean> isWriter, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(ReplyResponse.class, new Class<?>[]{long.class, String.class, String.class, String.class, boolean.class, java.time.LocalDateTime.class}, id, profile, nickname, content, isWriter, createdAt);
    }

}

