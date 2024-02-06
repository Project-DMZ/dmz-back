package com.dmz.api.community.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.repository.CommunityDslRepository;
import com.dmz.api.community.repository.CommunityRepository;
import com.dmz.api.member.domain.Member;
import com.dmz.api.member.repository.MemberRepository;
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
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public Response<?> getCommunityDetail(Long communityId, Long memberId) {

		return Response.ok(communityDslRepository.getCommunityDetail(communityId, memberId));
	}

	@Transactional(readOnly = true)
	public Response<?> getCommunityList(CommunitySearch search) {

		PageRequest pageable = search.getPageable(search);

		return Response.list(communityDslRepository.selectCommunityList(search, pageable));
	}

	@Transactional
	public Response<?> addCommunity(CommunityInsertRequest request, Member member) {

		Community community = CommunityInsertRequest.of(request, member);

		communityRepository.save(community);// 게시판 생성

		return Response.ok();
	}

	@Transactional
	public Response<?> deleteCommunity(Long communityId) {

		communityRepository.deleteById(communityId);

		return Response.ok();
	}
}
