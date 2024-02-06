package com.dmz.api.community.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dmz.api.community.dto.response.QCommunityResponse is a Querydsl Projection type for CommunityResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommunityResponse extends ConstructorExpression<CommunityResponse> {

    private static final long serialVersionUID = 1196763823L;

    public QCommunityResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<com.dmz.api.community.enums.CommunityType> type, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> profile, com.querydsl.core.types.Expression<java.time.LocalDate> closingDate, com.querydsl.core.types.Expression<Boolean> isLike, com.querydsl.core.types.Expression<Long> viewCount, com.querydsl.core.types.Expression<Integer> replyCount, com.querydsl.core.types.Expression<String> stacks, com.querydsl.core.types.Expression<String> positions) {
        super(CommunityResponse.class, new Class<?>[]{long.class, com.dmz.api.community.enums.CommunityType.class, String.class, String.class, java.time.LocalDate.class, boolean.class, long.class, int.class, String.class, String.class}, id, type, title, profile, closingDate, isLike, viewCount, replyCount, stacks, positions);
    }

}

