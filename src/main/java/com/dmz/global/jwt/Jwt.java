package com.dmz.global.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dmz.global.exception.UnAuthorized;

/**
 * packageName    : com.idenit.global.utils
 * fileName       : Jwt
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
public class Jwt {

	/**
	 * 회원 id 추출
	 *
	 * @return member_pk
	 */
	public static Long getId() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication.getName().equals("anonymousUser")) {
			throw new UnAuthorized();
		}

		return Long.valueOf(authentication.getName());
	}

	public static Long getNonId() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication.getName().equals("anonymousUser")) {
			return -1L;
		}

		return Long.valueOf(authentication.getName());
	}

}
