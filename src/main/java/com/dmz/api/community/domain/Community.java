package com.dmz.api.community.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Process;
import com.dmz.api.member.domain.Member;
import com.dmz.global.entity.BaseTime;

import jakarta.persistence.CascadeType;
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
 * packageName    : com.dmz.api.content.domain
 * fileName       : Content
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
@Comment("게시물 - 프로젝트,스터디")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@OneToMany(mappedBy = "community" , cascade = CascadeType.ALL)
	private List<TechStack> techStackList = new ArrayList<>();

	@OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
	private List<Reply> replyList;

	@OneToMany(mappedBy = "community" , cascade = CascadeType.ALL)
	private List<TechPosition> techPositionList = new ArrayList<>();

	@Comment("제목")
	@Column(nullable = false, length = 30)
	private String title;

	@Comment("게시물의 타입")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CommunityType type;

	@Comment("내용")
	@Column(nullable = false, length = 500)
	private String content;

	@Comment("모집 마감일")
	@Column(nullable = false)
	private LocalDate closingDate;

	@Comment("시작일")
	@Column(nullable = false)
	private LocalDate startDate;

	@Comment("마감일")
	@Column(nullable = false)
	private LocalDate endDate;

	@Comment("조회수")
	private Long viewCount;

	@Comment("진행방식")
	@Enumerated(EnumType.STRING)
	private Process process;

	@Builder
	public Community(Member member, List<TechStack> techStackList, List<Reply> replyList,
		List<TechPosition> techPositionList, String title, CommunityType type, String content, LocalDate closingDate,
		LocalDate startDate, LocalDate endDate, Long viewCount, Process process) {
		this.member = member;
		this.techStackList = techStackList;
		this.replyList = replyList;
		this.techPositionList = techPositionList;
		this.title = title;
		this.type = type;
		this.content = content;
		this.closingDate = closingDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.viewCount = viewCount;
		this.process = process;
	}
}
