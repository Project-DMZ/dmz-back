package com.dmz.api.community.repository;

import static com.dmz.api.community.domain.QCommunity.*;
import static com.dmz.api.community.domain.QReply.*;
import static com.querydsl.core.types.dsl.Expressions.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.dto.response.CommunityResponse;
import com.dmz.api.community.dto.response.QCommunityResponse;
import com.dmz.api.community.dto.response.detail.CommunityDetailBuilder;
import com.dmz.api.community.dto.response.detail.CommunityDetailResponse;
import com.dmz.api.community.dto.response.detail.QCommunityDetailResponse;
import com.dmz.api.community.dto.response.detail.QReplyResponse;
import com.dmz.api.community.dto.response.detail.ReplyResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.repository
 * fileName       : CommunityDslRepository
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Repository
@RequiredArgsConstructor
public class CommunityDslRepository {
	private final JPAQueryFactory queryFactory;

	//타입
	//제목
	//프로필이미지
	//포지션
	//기술
	//마감일
	//좋아요여부
	//조회수
	//댓글수
	public Page<?> selectCommunityList(CommunitySearch search, Pageable pageable) {

		List<CommunityResponse> content = queryFactory.select(new QCommunityResponse(
				community.id,
				community.type,
				community.title,
				community.member.profile,
				community.closingDate,
				FALSE,
				community.viewCount,
				community.replyList.size(),
				community.stack,
				community.positions
			)).from(community)
			.where(
				community.type.eq(search.getType()),
				keywordContains(search),
				stackContains(search),
				positionContains(search)
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long count = queryFactory.select(community.count())
			.from(community)
			.where(
				community.type.eq(search.getType()),
				keywordContains(search),
				stackContains(search),
				positionContains(search)
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, count != null ? count : 0L);
	}

	public CommunityDetailBuilder getCommunityDetail(Long communityId, Long memberId) {

		CommunityDetailResponse c = queryFactory.select(new QCommunityDetailResponse(
				community.id,
				community.title,
				community.viewCount,
				TRUE,
				community.member.profile,
				community.member.nickname,
				community.closingDate,
				community.type,
				community.startDate,
				community.endDate,
				community.process,
				community.content,
				community.member.id.eq(memberId),
				community.stack,
				community.positions
			)).from(community)
			.where(community.id.eq(communityId))
			.fetchOne();

		List<ReplyResponse> r = queryFactory.select(new QReplyResponse(
				reply.id,
				reply.member.profile,
				reply.member.nickname,
				reply.content,
				reply.member.id.eq(memberId),
				reply.createdAt
			))
			.from(reply)
			.join(reply.community)
			.where(reply.community.id.eq(communityId))
			.orderBy(reply.createdAt.desc())
			.fetch();

		return CommunityDetailBuilder.of(c, r);
	}

	private BooleanExpression keywordContains(CommunitySearch search) {
		return StringUtils.hasText(search.getKeyword()) ?
			community.content.contains(search.getKeyword()).or(community.title.contains(search.getKeyword())) : null;
	}

	private BooleanExpression stackContains(CommunitySearch search) {
		if (search.getTechList().size() > 0) {
			return search.getTechList().stream()
				.map(s -> community.stack.contains(s.name()))
				.reduce(BooleanExpression::or)
				.orElse(null);
		}
		return null;
	}

	private BooleanExpression positionContains(CommunitySearch search) {
		if (search.getPositionList().size() > 0) {
			return search.getPositionList().stream()
				.map(p -> community.positions.contains(p.name()))
				.reduce(BooleanExpression::or)
				.orElse(null);
		}
		return null;
	}
}
