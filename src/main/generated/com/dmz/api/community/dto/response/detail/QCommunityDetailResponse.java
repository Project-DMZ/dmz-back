package com.dmz.api.community.dto.response.detail;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dmz.api.community.dto.response.detail.QCommunityDetailResponse is a Querydsl Projection type for CommunityDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommunityDetailResponse extends ConstructorExpression<CommunityDetailResponse> {

    private static final long serialVersionUID = 1101823449L;

    public QCommunityDetailResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Long> viewCount, com.querydsl.core.types.Expression<Boolean> isLike, com.querydsl.core.types.Expression<String> profile, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<java.time.LocalDate> closingDate, com.querydsl.core.types.Expression<com.dmz.api.community.enums.CommunityType> type, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<com.dmz.api.community.enums.Process> process, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Boolean> isWriter, com.querydsl.core.types.Expression<String> stacks, com.querydsl.core.types.Expression<String> positions) {
        super(CommunityDetailResponse.class, new Class<?>[]{long.class, String.class, long.class, boolean.class, String.class, String.class, java.time.LocalDate.class, com.dmz.api.community.enums.CommunityType.class, java.time.LocalDate.class, java.time.LocalDate.class, com.dmz.api.community.enums.Process.class, String.class, boolean.class, String.class, String.class}, id, title, viewCount, isLike, profile, nickname, closingDate, type, startDate, endDate, process, content, isWriter, stacks, positions);
    }

}

