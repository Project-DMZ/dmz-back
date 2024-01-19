package com.dmz.api.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.Reply;
import com.dmz.api.community.dto.request.ReplyInsertRequest;
import com.dmz.api.community.exception.community.CommunityNotFoundException;
import com.dmz.api.community.exception.reply.ReplyNotFoundException;
import com.dmz.api.community.repository.CommunityRepository;
import com.dmz.api.community.repository.ReplyRepository;
import com.dmz.api.member.domain.Member;
import com.dmz.global.utils.Response;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.service
 * fileName       : ReplyService
 * author         : MinKyu Park
 * date           : 2024-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-20        MinKyu Park       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ReplyService {
	private final CommunityRepository communityRepository;
	private final ReplyRepository replyRepository;

	public Response<?> addReply(Long communityId, ReplyInsertRequest request, Member member) {

		Community community = communityRepository.findById(communityId).orElseThrow(CommunityNotFoundException::new);

		Reply saveReply = ReplyInsertRequest.of(community, request, member);

		replyRepository.save(saveReply);

		return Response.ok();
	}

	@Transactional
	public Response<?> updateReply(Long replyId, ReplyInsertRequest request) {

		Reply reply = replyRepository.findById(replyId).orElseThrow(ReplyNotFoundException::new);

		reply.update(request.getContent());

		return Response.ok();
	}
}
