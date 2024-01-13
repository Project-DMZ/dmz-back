package com.dmz.api.community.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.community.enums
 * fileName       : Position
 * author         : MinKyu Park
 * date           : 2024-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-03        MinKyu Park       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum Position {

	PLANNER("기획자"),
	DESIGNER("디자이너"),
	FRONTEND("프론트엔드"),
	BACKEND("서버/백엔드"),
	DEVOPS("데브옵스");

	private final String description;
}
