package com.dmz.api.community.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.dmz.api.content.enums
 * fileName       : TechStack
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
public enum Tech {
	// Language
	JAVA, JAVASCRIPT, PYTHON, GO, PHP, C, C_HASH, C_PLUS, DOT_NET,

	// DB
	MYSQL, POSTGRESQL, ORACLE, MONGO, MARIA, REDIS, DYNAMO,

	//FrameWork
	SPRING, SPRINGBOOT, NEST,

	//Library
	REACT,

	//Infra
	DOCKER, K8S, AWS

}
