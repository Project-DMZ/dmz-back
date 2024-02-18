package com.dmz.api.community.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Position;
import com.dmz.api.community.enums.Tech;
import com.dmz.global.utils.paging.Page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.dmz.api.community.dto.request
 * fileName       : CommunitySearch
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@Setter
public class CommunitySearch extends Page {
	@Schema(example = "STUDY", description = "STUDY:스터디, PROJECT:프로젝트, MENTORING:멘토링")
	private CommunityType type;

	@Schema(example = "검색어", description = "제목 또는 내용 검색")
	private String keyword;

	@Schema(example = "true", description = "모집중인 게시글만 보기 - true / false")
	private Boolean isRecruiting;

	private List<Tech> techList = new ArrayList<>();

	private List<Position> positionList = new ArrayList<>();

}
