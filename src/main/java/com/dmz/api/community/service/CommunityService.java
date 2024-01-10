package com.dmz.api.community.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.TechPosition;
import com.dmz.api.community.domain.TechStack;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.enums.Position;
import com.dmz.api.community.enums.Tech;
import com.dmz.api.community.repository.CommunityDslRepository;
import com.dmz.api.community.repository.CommunityRepository;
import com.dmz.api.community.repository.TechPositionRepository;
import com.dmz.api.community.repository.TechStackRepository;
import com.dmz.api.member.domain.Member;
import com.dmz.api.member.exception.MemberNotFoundException;
import com.dmz.api.member.repository.MemberRepository;
import com.dmz.global.constants.GetData;
import com.dmz.global.utils.Response;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.service
 * fileName       : CommunityService
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommunityService {
	private final CommunityDslRepository communityDslRepository;
	private final CommunityRepository communityRepository;
	private final TechStackRepository techStackRepository;
	private final TechPositionRepository positionRepository;
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public Response<?> getCommunityDetail(Long communityId, Long memberId) {

		return Response.ok(communityDslRepository.getCommunityDetail(communityId,memberId));
	}

	@Transactional(readOnly = true)
	public Response<?> getCommunityList(CommunitySearch search) {

		PageRequest pageable = search.getPageable(search);

		return Response.list(communityDslRepository.selectCommunityList(search, pageable));
	}

	@Transactional
	public Response<?> addCommunity(CommunityInsertRequest request, Long memberId) {

		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

		Community community = CommunityInsertRequest.of(request, member);

		List<TechStack> techStacks = request.getTechList().stream()
			.map(t -> getTechStack(community, t)).toList();

		List<TechPosition> techPositions = request.getPositionList().stream()
			.map(p -> getPosition(community, p)).toList();

		techStackRepository.saveAll(techStacks); // 기술스택 디비에 넣고

		positionRepository.saveAll(techPositions); // 포지션 디비에 넣고

		communityRepository.save(community); // 게시판 생성

		return Response.ok();
	}


	private TechStack getTechStack(Community community, Tech tech) {
		return TechStack.builder().tech(tech).community(community).build();
	}

	private TechPosition getPosition(Community community, Position position) {
		return TechPosition.builder().position(position).community(community).build();
	}

}
