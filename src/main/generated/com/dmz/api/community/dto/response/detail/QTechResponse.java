package com.dmz.api.community.dto.response.detail;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.dmz.api.community.dto.response.detail.QTechResponse is a Querydsl Projection type for TechResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTechResponse extends ConstructorExpression<TechResponse> {

    private static final long serialVersionUID = 103704025L;

    public QTechResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<com.dmz.api.community.enums.Tech> tech) {
        super(TechResponse.class, new Class<?>[]{long.class, com.dmz.api.community.enums.Tech.class}, id, tech);
    }

}

