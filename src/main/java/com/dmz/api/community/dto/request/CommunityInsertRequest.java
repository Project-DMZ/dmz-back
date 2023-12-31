package com.dmz.api.community.dto.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Process;
import com.dmz.api.community.enums.Tech;
import com.dmz.api.member.domain.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.community.dto.request
 * fileName       : CommunityInsertReqeust
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityInsertRequest {

	@Schema(example = "프로젝트 팀원 모집합니다", description = "게시글 제목")
	private String title;
	@Schema(example = "지속적으로 소통하며 함께 할 수 있는 팀원 모집합니다", description = "게시글 내용")
	private String content;
	@Schema(example = "STUDY", description = "STUDY:스터디, PROJECT:프로젝트, MENTORING:멘토링")
	private CommunityType type;
	@Schema(example = "2023-12-10", description = "모집 마감일")
	private LocalDate closingDate;
	@Schema(example = "2024-01-10", description = "시작일")
	private LocalDate startDate;
	@Schema(example = "2024-02-29", description = "마감일")
	private LocalDate endDate;
	@Schema(example = "ONLINE", description = "ONLINE , OFFLINE - 진행방식")
	private Process process;

	@Schema(type = "array", example = "[\"JAVA\", \"REACT\"]", description = "기술스택 목록")
	private final List<Tech> techList = new ArrayList<>();

	public static Community of(CommunityInsertRequest req , Member member) {
		return Community.builder()
			.title(req.title)
			.member(member)
			.content(req.content)
			.type(req.type)
			.closingDate(req.closingDate)
			.startDate(req.startDate)
			.endDate(req.endDate)
			.process(req.process)
			.viewCount(0L)
			.build();
	}
}
