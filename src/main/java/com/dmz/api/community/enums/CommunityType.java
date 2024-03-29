package com.dmz.api.community.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.content.enums
 * fileName       : ContentType
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum CommunityType {

	STUDY("스터디"),
	PROJECT("프로젝트"),
	MENTORING("멘토링");

	private final String description;

}
