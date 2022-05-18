package com.prgrms.jpapairboard.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.jpapairboard.user.entity.User;
import com.prgrms.jpapairboard.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
class UserApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Nested
    @DisplayName("addUser")
    class Describe_addUser {

        private static final String URL = "/api/v1/users";

        @Nested
        @DisplayName("필수 값이 모두 존재하면")
        class Context_with_required_data_exist {

            @Test
            @DisplayName("create 를 응답한다")
            void It_response_create() throws Exception {
                final Map<String, String> requestMap = new HashMap<>();
                requestMap.put("email", "san9580@naver.com");
                requestMap.put("name", "san kim");
                final var content = objectMapper.writeValueAsString(requestMap);

                final var request = MockMvcRequestBuilders.post(URL)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON);

                final var result = mockMvc.perform(request);
                verify(userService, times(1)).saveUser(any(User.class));
                result.andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("모든 값이 존재하면")
        class Context_with_all_data_exist {

            @Test
            @DisplayName("create 를 응답한다")
            void It_response_create() throws Exception {
                final Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("email", "san9580@naver.com");
                requestMap.put("name", "san kim");
                requestMap.put("age", 29);
                requestMap.put("hobby", "soccer");
                final var content = objectMapper.writeValueAsString(requestMap);

                final var request = MockMvcRequestBuilders.post(URL)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON);

                final var result = mockMvc.perform(request);
                verify(userService, times(1)).saveUser(any(User.class));
                result.andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("name 이 존재하지 않으면")
        class Context_with_name_null {

            @Test
            @DisplayName("BadRequest 를 응답한다")
            void It_response_BadRequest() {

            }
        }

        @Nested
        @DisplayName("email 이 존재하지 않으면")
        class Context_with_email_null {

            @Test
            @DisplayName("BadRequest 를 응답한다")
            void It_response_BadRequest() {

            }
        }

        @Nested
        @DisplayName("email 이 유효하지 않은 형태면")
        class Context_with_email_invalid {

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
    }
}
