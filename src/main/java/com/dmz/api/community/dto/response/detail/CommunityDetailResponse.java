package com.dmz.api.community.dto.response.detail;

import java.time.LocalDate;

import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Process;
import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.dto.response
 * fileName       : CommunityDetailResponse
 * author         : MinKyu Park
 * date           : 2024-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-03        MinKyu Park       최초 생성
 */
@Getter
@Builder
public class CommunityDetailResponse {

	@Schema(example = "식별자", description = "")
	private Long id;

	@Schema(example = "제목", description = "")
	private String title;

	@Schema(example = "조회수", description = "")
	private Long viewCount;

	@Schema(example = "좋아요 클릭여부 true / false", description = "")
	private Boolean isLike;

	@Schema(example = "프로필", description = "")
	private String profile;

	@Schema(example = "닉네임", description = "")
	private String nickname;

	@Schema(example = "모집 마감일", description = "")
	private LocalDate closingDate;

	@Schema(example = "모집 구분", description = "")
	private CommunityType type;

	@Schema(example = "시작 날짜", description = "")
	private LocalDate startDate;

	@Schema(example = "마감 날짜", description = "")
	private LocalDate endDate;

	@Schema(example = "진행방식", description = "")
	private Process process;

	@Schema(example = "내용", description = "")
	private String content;
	private Boolean isWriter;

	private String stacks;
	private String positions;

	@QueryProjection
	public CommunityDetailResponse(Long id, String title, Long viewCount, Boolean isLike, String profile,
		String nickname,
		LocalDate closingDate, CommunityType type, LocalDate startDate, LocalDate endDate, Process process,
		String content,
		Boolean isWriter, String stacks, String positions) {
		this.id = id;
		this.title = title;
		this.viewCount = viewCount;
		this.isLike = isLike;
		this.profile = profile;
		this.nickname = nickname;
		this.closingDate = closingDate;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.process = process;
		this.content = content;
		this.isWriter = isWriter;
		this.stacks = stacks;
		this.positions = positions;
	}
}
