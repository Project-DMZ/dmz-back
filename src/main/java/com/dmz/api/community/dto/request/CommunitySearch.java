package com.dmz.api.community.dto.request;

import com.dmz.api.community.enums.CommunityType;
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
}
