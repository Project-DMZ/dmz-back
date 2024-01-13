package com.dmz.api.community.dto.response.detail;

import com.dmz.api.community.enums.Tech;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : TechResponse
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
public class TechResponse {
	private Long id;
	private Tech tech;

	@QueryProjection
	public TechResponse(Long id, Tech tech) {
		this.id = id;
		this.tech = tech;
	}
}
