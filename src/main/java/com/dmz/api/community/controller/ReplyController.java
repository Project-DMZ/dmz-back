package com.dmz.api.community.controller;

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
	public Response<?> addReply(@PathVariable Long communityId, @RequestBody ReplyInsertRequest request) {

		return replyService.addReply(communityId,request,getData.member(1L));
	}
}
