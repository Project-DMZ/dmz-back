package com.dmz.api.community.dto.response.detail;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : CommunityDetailBuilder
 * author         : MinKyu Park
 * date           : 2024-01-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-06        MinKyu Park       최초 생성
 */
@Getter
@Builder
public class CommunityDetailBuilder {
	private CommunityDetailResponse community;
	private List<ReplyResponse> replyList;

	public static CommunityDetailBuilder of(CommunityDetailResponse community,List<ReplyResponse> replyList) {
		return CommunityDetailBuilder.builder()
			.community(community)
			.replyList(replyList)
			.build();
	}
}
