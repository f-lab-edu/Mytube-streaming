package com.flab.Mytube.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.Mytube.domain.LiveStreaming;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LiveControllerTest {

  private ObjectMapper objectMapper = new ObjectMapper();

  MockMvc mockMvc;
  @Autowired
  private WebApplicationContext wac;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        .addFilter(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }


  @Test
  @DisplayName("라이브 시작 Test")
  public void getReservation() throws Exception {
    String title = "TEST CODE RESERVE";
    String contents = "HI";
    String inputString = "2024-08-21T13:30";
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    LocalDate reservedAt = LocalDate.parse(inputString, parser);

    LiveStreaming streaming = LiveStreaming.builder()
        .channelId(3)
        .movieId(179)
        .title(title)
        .contents(contents)
        .reservedAt(reservedAt)
        .build();

    this.mockMvc
        .perform(post("/api/v1/lives")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(streaming)
            ))
        .andExpect(status().isOk())
        .andDo(print());
  }

}