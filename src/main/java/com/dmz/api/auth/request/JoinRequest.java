package com.dmz.api.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.idenit.api.auth.request
 * fileName       : JoinRequest
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Getter
@NoArgsConstructor
public class JoinRequest {

	// @NotBlank(message = "id: 아이디는 필수 값입니다.")
	private String providerId;

	// @NotBlank(message = "password: 비밀번호는 필수 값입니다.")
	@Schema(example = "1234", description = "")
	private String password;

	// @NotBlank(message = "name: 사용자 이름는 필수 값입니다.")
	@Schema(example = "DMZ001", description = "")
	private String nickname;

	// @NotBlank(message = "phone: 휴대폰 번호는 필수 값입니다.")
	// @Pattern(regexp = "^010[0-9]{7,8}$", message = "phone: 휴대폰 번호를 정확하게 입력해 주세요.")
	// private String phone;

	// @Email(message = "email: 이메일 형식이 올바르지 않습니다.")
	@Schema(example = "dmz@dmz.com", description = "")
	private String email;

	@Builder
	public JoinRequest(String providerId, String password, String nickname, String email) {
		this.providerId = providerId;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}
}
