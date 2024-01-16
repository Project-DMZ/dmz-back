package com.dmz.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * packageName    : com.idenit.global.config
 * fileName       : QueryDslConfig
 * author         : Hyuk Kim
 * date           : 2023-09-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-20        Hyuk Kim       최초 생성
 */
@Configuration
public class QueryDslConfig {

	@PersistenceContext
	private EntityManager em;

	@Bean
	public JPAQueryFactory query() {

		return new JPAQueryFactory(JPQLTemplates.DEFAULT,em);
	}

}
