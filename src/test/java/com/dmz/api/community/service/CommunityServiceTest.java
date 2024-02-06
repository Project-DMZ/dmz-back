package com.dmz.api.community.service;

import static com.dmz.api.community.enums.CommunityType.*;
import static com.dmz.api.community.enums.Position.*;
import static com.dmz.api.community.enums.Tech.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.domain.TechPosition;
import com.dmz.api.community.domain.TechStack;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.dto.request.CommunitySearch;
import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Position;
import com.dmz.api.community.enums.Process;
import com.dmz.api.community.enums.Tech;
import com.dmz.api.community.exception.community.CommunityNotFoundException;
import com.dmz.api.community.repository.CommunityDslRepository;
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

	@Autowired
	private CommunityDslRepository communityDslRepository;

	@AfterEach
	void tearDown() {
		techStackRepository.deleteAllInBatch();
		positionRepository.deleteAllInBatch();
		communityRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
	}

	@BeforeEach
	void setUp() {
		Member member = Member.builder()
			.email("setUp@dmz.com")
			.password("1234")
			.nickname("setUpNickname")
			.providerId("setUpProviderId")
			.profile("setUpProfileImage.jpg")
			.build();

		memberRepository.save(member);

			Community community = Community.builder()
				.title("setUpTitle")
				.content("setUpContent")
				.type(CommunityType.STUDY)
				.closingDate(LocalDate.parse("2023-12-10"))
				.startDate(LocalDate.parse("2024-01-10"))
				.endDate(LocalDate.parse("2024-02-29"))
				.process(Process.ONLINE)
				.build();

			Community save = communityRepository.save(community);

			List<TechStack> techStacks = Stream.of(JAVA, REACT)
				.map(t -> TechStack.builder().tech(t).community(save).build())
				.toList();

			List<TechPosition> techPositions = Stream.of(BACKEND, FRONTEND)
				.map(p -> TechPosition.builder().position(p).community(save).build())
				.toList();

			techStackRepository.saveAll(techStacks);
			positionRepository.saveAll(techPositions);

	}

	@Test
	@DisplayName("게시물을 단일 조회한다")
	void getCommunityDetail() {
		// given
		Community findCommunity = communityRepository.findAll().get(0);

		Community community = communityRepository.findById(findCommunity.getId()).orElseThrow(CommunityNotFoundException::new);

		// when
		List<TechStack> stacks1 = techStackRepository.findByCommunity(community);
		List<TechPosition> positions1 = positionRepository.findByCommunity(community);

		// then
		assertThat(community.getTitle()).isEqualTo("setUpTitle");
		assertThat(community.getContent()).isEqualTo("setUpContent");
		assertThat(community.getProcess()).isEqualByComparingTo(Process.ONLINE);
		assertThat(stacks1).hasSize(2)
			.extracting("tech")
			.containsExactlyInAnyOrder(JAVA, REACT);
		assertThat(positions1).hasSize(2)
			.extracting("position")
			.containsExactlyInAnyOrder(BACKEND, FRONTEND);
	}

	@Test
	@DisplayName("커뮤니티 글을 작성한다")
	void addCommunity() {
		// Given
		Member member = Member.builder()
			.email("insertTest@dmz.com")
			.password("1234")
			.nickname("InsertTester")
			.providerId("providerIdInsertTester")
			.profile("profileImage.jpg")
			.build();

		memberRepository.save(member);

		CommunityInsertRequest request = CommunityInsertRequest.builder()
			.title("insertTestTitle")
			.content("insertTestContent")
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
		Community community = (Community)communityService.addCommunity(request, member).getData();
		List<TechStack> stacks = techStackRepository.findByCommunity(community);
		List<TechPosition> positions = positionRepository.findByCommunity(community);

		// Then
		assertThat(community.getTitle()).isEqualTo("insertTestTitle");
		assertThat(community.getProcess()).isEqualByComparingTo(Process.ONLINE);
		assertThat(stacks).hasSize(2)
			.extracting("tech")
			.containsExactlyInAnyOrder(JAVA, REACT);
		assertThat(positions).hasSize(2)
			.extracting("position")
			.containsExactlyInAnyOrder(BACKEND, FRONTEND);
	}



	@Test
	@DisplayName("게시물 목록을 페이지,타입별로 조회한다")
	void getCommunityList() {
		// given
		Member member = Member.builder()
			.email("list@dmz.com")
			.password("1234")
			.nickname("listUser")
			.providerId("listUserProviderId")
			.profile("listUserProfileImage.jpg")
			.build();

		memberRepository.save(member);

		for (int i = 1; i <= 5; i++) {
			Community community = Community.builder()
				.title("setUpTitle" + i)
				.content("setUpContent" + i)
				.type(CommunityType.STUDY)
				.closingDate(LocalDate.parse("2023-12-10"))
				.startDate(LocalDate.parse("2024-01-10"))
				.endDate(LocalDate.parse("2024-02-29"))
				.process(Process.ONLINE)
				.build();

			Community save = communityRepository.save(community);

			List<TechStack> techStacks = Stream.of(JAVA, REACT)
				.map(t -> TechStack.builder().tech(t).community(save).build())
				.toList();

			List<TechPosition> techPositions = Stream.of(BACKEND, FRONTEND)
				.map(p -> TechPosition.builder().position(p).community(save).build())
				.toList();

			techStackRepository.saveAll(techStacks);
			positionRepository.saveAll(techPositions);
		}

		CommunitySearch search = new CommunitySearch();
		search.setType(STUDY);
		Pageable pageable = PageRequest.of(0, 2);

		// when
		// Page<CommunityResponse> communityList = communityDslRepository.selectCommunityList(search, pageable);

		// then
	}
}