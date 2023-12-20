package com.idenit.api.auth.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * packageName    : com.idenit.api.auth.request
 * fileName       : LoginRequest
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
public class LoginRequest {

	@NotBlank(message = "id: 아이디는 필수 값입니다.")
	private String id;

	@NotBlank(message = "password: 비밀번호는 필수 값입니다.")
	private String password;

	public UsernamePasswordAuthenticationToken toAuthentication() {
		return new UsernamePasswordAuthenticationToken(id, password);
	}

}
