package com.dmz.support;

import com.dmz.api.community.controller.CommunityController;
import com.dmz.api.community.service.CommunityService;
import com.dmz.api.member.repository.MemberRepository;
import com.dmz.global.constants.GetData;
import com.dmz.global.filter.JwtFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {CommunityController.class}
)
public abstract class IntegrateControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected MemberRepository memberRepository;

    @MockBean
    CommunityService communityService;

    @MockBean
    GetData getData;

    @MockBean
    JwtFilter jwtFilter;

    @Autowired
    protected ObjectMapper om;
}
