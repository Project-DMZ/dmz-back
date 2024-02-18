package com.dmz.api.community.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmz.api.community.dto.request.ReplyInsertRequest;
import com.dmz.api.community.service.ReplyService;
import com.dmz.global.constants.GetData;
import com.dmz.global.utils.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.controller
 * fileName       : ReplyController
 * author         : MinKyu Park
 * date           : 2024-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-20        MinKyu Park       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
@Tag(name = "댓글")
public class ReplyController {
	private final ReplyService replyService;
	private final GetData getData;

	@PostMapping("/{communityId}")
	@Operation(summary = "댓글 작성", description = "")
	public Response<?> addReply(@PathVariable(name = "communityId") Long communityId, @RequestBody ReplyInsertRequest request) {
		// 로그인 임시처리
		return replyService.addReply(communityId, request, getData.member(1L));
	}

	@PatchMapping("/{replyId}")
	@Operation(summary = "댓글 수정", description = "")
	public Response<?> updateReply(@PathVariable Long replyId, @RequestBody ReplyInsertRequest request) {

		return replyService.updateReply(replyId, request);
	}

	@DeleteMapping("/{replyId}")
	@Operation(summary = "댓글 삭제", description = "")
	public Response<?> deleteReply(@PathVariable Long replyId) {

		return replyService.deleteReply(replyId);
	}
}
