package com.dmz.api.community.domain;

import org.hibernate.annotations.Comment;

import com.dmz.api.community.enums.Position;
import com.dmz.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.dmz.api.community.domain
 * fileName       : TechPosition
 * author         : MinKyu Park
 * date           : 2024-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-03        MinKyu Park       최초 생성
 */
@Entity
@Getter
@Comment("모집 포지션")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechPosition extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Community community;

	@Comment("모집 포지션")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Position position;

	@Builder
	public TechPosition(Community community, Position position) {
		this.community = community;
		this.position = position;
	}
}
