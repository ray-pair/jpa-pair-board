package com.prgrms.jpapairboard.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.jpapairboard.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostApiController.class)
class PostApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PostService postService;

    @Nested
    @DisplayName("addPost 메서드는")
    class Describe_addPost {

        private static final String URL = "/api/v1/posts";

        @Nested
        @DisplayName("userId 값이 양수이고, content 가 존재하고, title 의 길이가 50이하이면")
        class Context_with_validData {

            @Test
            @DisplayName("created 를 응답한다")
            void It_ResponseCreated() throws Exception {
                //given
                final var requestMap = new HashMap<String, Object>();
                requestMap.put("title", "test");
                requestMap.put("content", "testContent");
                requestMap.put("userId", 1);

                //when, then
                mockMvc.perform(post(URL)
                       .contentType(APPLICATION_JSON)
                       .content(toJson(requestMap)))
                       .andExpect(status().isCreated());
            }
       }

        @Nested
        @DisplayName("userId 값이 양수가 아니거나, 존재하지 않는경우")
        class Context_with_UserIdInvalid {

            @ParameterizedTest
            @NullSource
            @ValueSource(longs = {0, -1, Long.MIN_VALUE})
            @DisplayName("BadRequest 를 응답한다")
            void It_(Long src) throws Exception {
                //given
                final var requestMap = new HashMap<String, Object>();
                requestMap.put("title", "test");
                requestMap.put("content", "testContent");
                requestMap.put("userId", src);

                //when, then
                mockMvc.perform(post(URL)
                       .contentType(APPLICATION_JSON)
                       .content(toJson(requestMap)))
                       .andExpect(status().isBadRequest());

            }
        }
    }

    private String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}