package com.dmz.api.community.repository;

import com.dmz.api.community.domain.Community;
import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.community.enums.CommunityType;
import com.dmz.api.community.enums.Process;
import com.dmz.api.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CommunityDslRepositoryTest {

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityDslRepository communityDslRepository;

    @DisplayName("조건에 맞는 커뮤니티 성격의 글을 조회한다.")
    @Test
    void selectCommunityList() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .id(1L)
                .profile("testProfile")
                .password("1234")
                .providerId("provider")
                .nickname("nickName")
                .build();
        CommunityInsertRequest request = new CommunityInsertRequest("title", "content", CommunityType.STUDY, LocalDate.of(2024, 3, 19),
                LocalDate.of(2024, 3, 19), LocalDate.of(2024, 3, 19), Process.ONLINE);

        Community community = CommunityInsertRequest.of(request, member);
        communityRepository.save(community);



        // when
        // then
    }
}