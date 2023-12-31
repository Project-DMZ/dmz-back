package com.dmz.api.member.exception;

import static com.dmz.global.enums.ErrorStatus.*;

import com.dmz.global.enums.ErrorStatus;
import com.dmz.global.exception.GlobalException;

/**
 * packageName    : com.dmz.api.member.exception
 * fileName       : MemberNotFoundException
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
public class MemberNotFoundException extends GlobalException {

	public MemberNotFoundException() {
		super("회원 정보를 찾을 수 없습니다.");
	}

	@Override
	public ErrorStatus code() {
		return N01;
	}
}
