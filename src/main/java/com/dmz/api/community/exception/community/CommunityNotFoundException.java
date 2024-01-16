package com.dmz.api.community.exception.community;

import com.dmz.global.enums.ErrorStatus;
import com.dmz.global.exception.GlobalException;

import lombok.Getter;

/**
 * packageName    : com.dmz.api.community.exception.community
 * fileName       : CommunityNotFoundException
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Getter
public class CommunityNotFoundException extends GlobalException {

	public CommunityNotFoundException() {
		super("게시물을 찾을 수 없습니다.");
	}

	@Override
	public ErrorStatus code() {
		return ErrorStatus.N01;
	}
}
