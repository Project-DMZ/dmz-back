package com.dmz.api.community.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.dmz.api.community.dto.response.detail.PositionResponse;
import com.dmz.api.community.dto.response.detail.TechResponse;
import com.dmz.api.community.enums.CommunityType;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : CommunityResponse
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@Builder
public class CommunityResponse {
	@Schema(example = "1", description = "게시글의 식별자")
	private Long id;
	@Schema(example = "STUDY", description = "STUDY:스터디, PROJECT:프로젝트, MENTORING:멘토링")
	private CommunityType type;
	@Schema(example = "프로젝트 팀원 모집합니다", description = "게시글 제목")
	private String title;
	@Schema(example = "profileImage.png", description = "프로필 이미지")
	private String profile;
	@Schema(example = "2024-02-29", description = "마감일")
	private LocalDate closingDate;
	@Schema(example = "true / false", description = "좋아요 클릭여부")
	private Boolean isLike;
	@Schema(example = "100", description = "조회수")
	private Long viewCount;
	@Schema(example = "15", description = "댓글수")
	private Integer replyCount;
	@Schema(type = "array", example = "[\"JAVA\", \"REACT\"]", description = "기술스택 목록")
	private List<TechResponse> techList;
	@Schema(type = "array", example = "[\"BACKEND\", \"FRONTEND\"]", description = "포지션 목록")
	private List<PositionResponse> positionList;

	@QueryProjection
	public CommunityResponse(Long id, CommunityType type, String title, String profile, LocalDate closingDate,
		Boolean isLike, Long viewCount, Integer replyCount, List<TechResponse> techList,
		List<PositionResponse> positionList) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.profile = profile;
		this.closingDate = closingDate;
		this.isLike = isLike;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.techList = techList;
		this.positionList = positionList;
	}
}
