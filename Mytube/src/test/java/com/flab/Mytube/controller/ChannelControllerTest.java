//package com.flab.Mytube.controller;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//class ChannelControllerTest {
//
//  MockMvc mockMvc;
//  @Autowired
//  private WebApplicationContext wac;
//
//  @BeforeEach
//  public void setup() {
//    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//        .addFilter(new CharacterEncodingFilter("UTF-8", true))
//        .build();
//  }
//
//  @Test
//  @DisplayName("user Id:3 이 게시한 라이브 목록 조회 Test")
//  void getLiveList() throws Exception {
//    this.mockMvc
//        .perform(get("/api/v1/channels/3"))
//        .andExpect(status().isOk())
//        .andDo(print());
//  }
//
//  @Test
//  @DisplayName("LiveId:22 정보 조회 Test")
//  void replayLive() throws Exception {
//    this.mockMvc
//        .perform(get("/api/v1/channels/lives/22/replay"))
//        .andExpect(status().isOk())
//        .andDo(print());
//  }
//  @Test
//  @DisplayName("삭제된 LiveId:15 정보 조회 Test")
//  void replayDeletedLive() throws Exception {
//    this.mockMvc
//        .perform(get("/api/v1/channels/lives/15/replay"))
//        .andExpect(status().isNotFound())
//        .andDo(print());
//  }
//}