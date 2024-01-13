package com.dmz.api.community.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.enums
 * fileName       : Process
 * author         : MinKyu Park
 * date           : 2023-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-30        MinKyu Park       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum Process {
	ONLINE("온라인"),
	OFFLINE("오프라인");

	private final String description;
}
