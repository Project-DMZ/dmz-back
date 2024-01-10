package com.dmz.api.community.service;

import static com.dmz.api.community.enums.Position.*;
import static com.dmz.api.community.enums.Tech.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.TechPosition;
import com.dmz.api.community.domain.TechStack;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Position;
import com.dmz.api.community.enums.Process;
import com.dmz.api.community.enums.Tech;
import com.dmz.api.community.exception.community.CommunityNotFoundException;
import com.dmz.api.community.repository.CommunityRepository;
import com.dmz.api.community.repository.TechPositionRepository;
import com.dmz.api.community.repository.TechStackRepository;
import com.dmz.api.member.domain.Member;
import com.dmz.api.member.repository.MemberRepository;

/**
 * packageName    : com.dmz.api.community.service
 * fileName       : CommunityServiceTest
 * author         : MinKyu Park
 * date           : 2024-01-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-08        MinKyu Park       최초 생성
 */
@SpringBootTest
@ActiveProfiles("test")
class CommunityServiceTest {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private CommunityRepository communityRepository;

	@Autowired
	private TechStackRepository techStackRepository;

	@Autowired
	private TechPositionRepository positionRepository;

	@Autowired
	private MemberRepository memberRepository;

	@AfterEach
	void tearDown() {
		techStackRepository.deleteAllInBatch();
		positionRepository.deleteAllInBatch();
		communityRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
	}

	@BeforeEach
	void init() {
		Member member = Member.builder()
			.email("dmz@dmz.com")
			.password("1234")
			.nickname("DMZ관리자")
			.providerId("asd56a4sd32")
			.profile("profileImage.jpg")
			.build();

		memberRepository.save(member);
	}

	@Test
	@DisplayName("커뮤니티 작성 테스트")
	void addCommunity() {
		// Given
		CommunityInsertRequest request = CommunityInsertRequest.builder()
			.title("프로젝트 팀원 모집합니다")
			.content("지속적으로 소통하며 함께 할 수 있는 팀원 모집합니다")
			.type(CommunityType.STUDY)
			.closingDate(LocalDate.parse("2023-12-10"))
			.startDate(LocalDate.parse("2024-01-10"))
			.endDate(LocalDate.parse("2024-02-29"))
			.process(Process.ONLINE)
			.build();

		List<Tech> techList = Arrays.asList(JAVA, REACT);
		request.setTechList(techList);

		List<Position> positionList = Arrays.asList(BACKEND, FRONTEND);
		request.setPositionList(positionList);

		// When
		communityService.addCommunity(request, 1L);
		Community community = communityRepository.findById(1L).orElseThrow(CommunityNotFoundException::new);
		List<TechStack> stacks = techStackRepository.findByCommunity(community);
		List<TechPosition> positions = positionRepository.findByCommunity(community);

		// Then
		assertThat(community.getTitle()).isEqualTo("프로젝트 팀원 모집합니다");
		assertThat(community.getProcess()).isEqualByComparingTo(Process.ONLINE);
		assertThat(stacks).hasSize(2)
		.extracting("id","tech")
		.containsExactlyInAnyOrder(
			tuple(1L,JAVA),
			tuple(2L,REACT)
		);
		assertThat(positions).hasSize(2)
			.extracting("id","position")
			.containsExactlyInAnyOrder(
				tuple(1L,BACKEND),
				tuple(2L,FRONTEND)
			);

	}

}