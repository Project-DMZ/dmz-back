package com.dmz.api.community.dto.response.detail;

import com.dmz.api.community.enums.Position;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : PositionResponse
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
public class PositionResponse {
	private Long id;
	private Position position;

	@QueryProjection
	public PositionResponse(Long id, Position position) {
		this.id = id;
		this.position = position;
	}
}
