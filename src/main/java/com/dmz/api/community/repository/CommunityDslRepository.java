package com.dmz.api.community.repository;

import static com.dmz.api.community.domain.QCommunity.*;
import static com.dmz.api.community.domain.QReply.*;
import static com.dmz.api.community.domain.QTechPosition.*;
import static com.dmz.api.community.domain.QTechStack.*;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.group.GroupBy.*;
import static com.querydsl.core.types.dsl.Expressions.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.dto.response.CommunityResponse;
import com.dmz.api.community.dto.response.QCommunityResponse;
import com.dmz.api.community.dto.response.detail.CommunityDetailBuilder;
import com.dmz.api.community.dto.response.detail.CommunityDetailResponse;
import com.dmz.api.community.dto.response.detail.PositionResponse;
import com.dmz.api.community.dto.response.detail.QCommunityDetailResponse;
import com.dmz.api.community.dto.response.detail.QPositionResponse;
import com.dmz.api.community.dto.response.detail.QReplyResponse;
import com.dmz.api.community.dto.response.detail.QTechResponse;
import com.dmz.api.community.dto.response.detail.ReplyResponse;
import com.dmz.api.community.dto.response.detail.TechResponse;
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
	public Page<CommunityResponse> selectCommunityList(CommunitySearch search, Pageable pageable) {

		List<CommunityResponse> content = queryFactory.selectFrom(community)
			.leftJoin(techPosition).on(techPosition.community.id.eq(community.id))
			.leftJoin(techStack).on(techStack.community.id.eq(community.id))
			.where(community.type.eq(search.getType()))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.transform(
				groupBy(community.id).list(
					new QCommunityResponse(
						community.id,
						community.type,
						community.title,
						community.member.profile,
						community.closingDate,
						FALSE,
						community.viewCount,
						community.replyList.size(),
						list(new QTechResponse(techStack.id, techStack.tech)),
						list(new QPositionResponse(techPosition.id, techPosition.position))
					)
				)
			);

		Long count = queryFactory.select(community.count())
			.from(community)
			.leftJoin(techPosition).on(techPosition.community.id.eq(community.id))
			.leftJoin(techStack).on(techStack.community.id.eq(community.id))
			.where(community.type.eq(search.getType()))
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
				community.member.id.eq(memberId)
			)).from(community)
			.fetchOne();

		List<PositionResponse> p = queryFactory.select(new QPositionResponse(
				techPosition.id,
				techPosition.position
			))
			.from(techPosition)
			.join(techPosition.community)
			.where(techPosition.community.id.eq(communityId))
			.fetch();

		List<TechResponse> t = queryFactory.select(new QTechResponse(
				techStack.id,
				techStack.tech
			))
			.from(techStack)
			.join(techStack.community)
			.where(techStack.community.id.eq(communityId))
			.fetch();

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

		return CommunityDetailBuilder.of(c, t, p, r);
	}
}
