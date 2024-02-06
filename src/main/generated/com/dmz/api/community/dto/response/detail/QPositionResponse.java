package com.dmz.api.community.dto.response.detail;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dmz.api.community.dto.response.detail.QPositionResponse is a Querydsl Projection type for PositionResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPositionResponse extends ConstructorExpression<PositionResponse> {

    private static final long serialVersionUID = -1564395412L;

    public QPositionResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<com.dmz.api.community.enums.Position> position) {
        super(PositionResponse.class, new Class<?>[]{long.class, com.dmz.api.community.enums.Position.class}, id, position);
    }

}

