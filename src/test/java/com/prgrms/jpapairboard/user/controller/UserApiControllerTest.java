package com.prgrms.jpapairboard.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.jpapairboard.user.entity.User;
import com.prgrms.jpapairboard.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Nested
    class Method_save_Test {

        private static final String URL = "/api/v1/users";

        @Nested
        class 필수_값이_모두_존재하는_입력이_들어오면 {

            @Test
            void Created_200을_응답한다() throws Exception {
                final Map<String, String> requestMap = new HashMap<>();
                requestMap.put("email", "san9580@naver.com");
                requestMap.put("name", "san kim");

                final var result = mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestMap)));

                verify(userService, times(1)).save(any(User.class));
                result.andExpect(status().isCreated());
                assertThat(result.andReturn().getResponse()).isNotNull();
            }
        }

        @Nested
        class 모든_값이_존재하는_입력이_들어오면 {

            @Test
            void create_200을_응답한다() throws Exception {
                final Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("email", "san9580@naver.com");
                requestMap.put("name", "san kim");
                requestMap.put("age", 29);
                requestMap.put("hobby", "soccer");

                final var result = mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestMap)));

                result.andExpect(status().isCreated());
                assertThat(result.andReturn().getResponse()).isNotNull();
            }
        }

        @Nested
        class 잘못된_name을_포함한_입력이_들어오면 {

            @ParameterizedTest
            @NullAndEmptySource
            void BadRequest_400을_응답한다(String inputName) throws Exception {
                final Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("email", "san9580@naver.com");
                requestMap.put("name", inputName);
                requestMap.put("age", 29);
                requestMap.put("hobby", "soccer");

                final var result = mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestMap)));

                result.andExpect(status().isBadRequest());
            }
        }

        @Nested
        class email이_누락된_입력이_들어오면 {

            @ParameterizedTest
            @NullAndEmptySource
            void BadRequest_400을_응답한다(String inputEmail) throws Exception {
                final Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("email", inputEmail);
                requestMap.put("name", "san");
                requestMap.put("age", 20);
                requestMap.put("hobby", "soccer");

                final var result = mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestMap)));

                result.andExpect(status().isBadRequest());
            }
        }

        @Nested
        class 유요하지_하지_않는_email을_포함한_입력이_들어오면 {

            @Test
            @DisplayName("BadRequest 를 응답한다")
            void It_response_BadRequest() {

            }
        }

        @Nested
        @DisplayName("age 가 음수이면")
        class Context_with_negative_age {

            @Test
            @DisplayName("BadRequest 를 응답한다")
            void It_response_BadRequest() {

            }
        }

        private String toJson(Object obj) throws JsonProcessingException {
            return objectMapper.writeValueAsString(obj);
        }
    }
}
