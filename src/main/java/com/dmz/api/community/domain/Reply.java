package com.dmz.api.community.domain;

import org.hibernate.annotations.Comment;

import com.dmz.api.member.domain.Member;
import com.dmz.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.content.domain
 * fileName       : Comment
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
@Entity
@Getter
@Comment("댓글")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Community community;

	@Comment("내용")
	@Column(nullable = false, length = 50)
	private String content;

	@Builder
	public Reply(Long id, Member member, Community community, String content) {
		this.id = id;
		this.member = member;
		this.community = community;
		this.content = content;
	}

	public void update(String content) {
		this.content = content;
	}
}
