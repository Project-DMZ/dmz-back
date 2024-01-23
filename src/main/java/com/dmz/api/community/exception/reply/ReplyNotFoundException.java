package com.dmz.api.community.exception.reply;

import com.dmz.global.enums.ErrorStatus;
import com.dmz.global.exception.GlobalException;

/**
 * packageName    : com.dmz.api.community.exception.reply
 * fileName       : ReplyNotFoundException
 * author         : MinKyu Park
 * date           : 2024-01-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-20        MinKyu Park       최초 생성
 */
public class ReplyNotFoundException extends GlobalException {

	private static final String MESSAGE = "댓글을 찾을 수 없습니다.";
	public ReplyNotFoundException() {
		super(MESSAGE);
	}

	@Override
	public ErrorStatus code() {
		return ErrorStatus.N01;
	}
}
