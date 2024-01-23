package com.dmz.api.community.dto.request;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.Reply;
import com.dmz.api.member.domain.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.community.dto.request
 * fileName       : ReplyInserRequest
 * author         : MinKyu Park
 * date           : 2024-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-20        MinKyu Park       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyInsertRequest {
	private String content;

	public static Reply of(Community c , ReplyInsertRequest r , Member m) {
		return Reply.builder()
			.community(c)
			.member(m)
			.content(r.getContent())
			.build();
	}
}
