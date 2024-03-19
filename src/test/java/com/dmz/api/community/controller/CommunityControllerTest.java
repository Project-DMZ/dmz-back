package com.dmz.api.community.controller;

import com.dmz.api.community.dto.request.CommunityInsertRequest;
import com.dmz.api.member.domain.Member;
import com.dmz.support.IntegrateControllerTestSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static com.dmz.api.community.enums.CommunityType.STUDY;
import static com.dmz.api.community.enums.Position.BACKEND;
import static com.dmz.api.community.enums.Position.FRONTEND;
import static com.dmz.api.community.enums.Process.ONLINE;
import static com.dmz.api.community.enums.Tech.*;


class CommunityControllerTest extends IntegrateControllerTestSupport {

    @BeforeEach
    void beforeEach() {
        Member member1 = Member.builder()
                .id(1L)
                .profile("profile")
                .email("test@test.com")
                .password("1234")
                .providerId("provider")
                .nickname("test1")
                .build();
        memberRepository.save(member1);

    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("요청에 따른 게시물을 등록합니다.")
    @Test
    void addCommunity() throws Exception {
        // given
        List<Member> all = memberRepository.findAll();
        System.out.println(">>>>>>>>>"+all.size());
        CommunityInsertRequest request = CommunityInsertRequest.builder()
                .title("title1")
                .startDate(LocalDate.of(2024, 3, 19))
                .endDate(LocalDate.of(2024, 3, 20))
                .process(ONLINE)
                .type(STUDY)
                .closingDate(LocalDate.of(2024, 3, 20))
                .build();

        request.setPositionList(List.of(BACKEND, FRONTEND));
        request.setTechList(List.of(JAVA, JAVASCRIPT, REACT));

        // when // then
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/community")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(om.writeValueAsBytes(request))
//                )
//                .andDo(print())
//                .andExpect(status().isOk());
    }

}