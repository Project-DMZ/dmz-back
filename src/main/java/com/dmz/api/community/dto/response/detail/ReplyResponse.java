package com.dmz.api.community.dto.response.detail;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : ReplyResponse
 * author         : MinKyu Park
 * date           : 2024-01-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-06        MinKyu Park       최초 생성
 */
@Getter
public class ReplyResponse {

	private Long id;
	private String profile;
	private String nickname;
	private String content;
	private LocalDateTime createdAt;
	private Boolean isWriter;

	@QueryProjection
	public ReplyResponse(Long id, String profile, String nickname, String content, Boolean isWriter,
		LocalDateTime createdAt) {
		this.id = id;
		this.profile = profile;
		this.nickname = nickname;
		this.content = content;
		this.createdAt = createdAt;
		this.isWriter = isWriter;
	}
}
