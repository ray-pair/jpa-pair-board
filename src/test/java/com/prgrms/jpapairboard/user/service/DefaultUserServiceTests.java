package com.prgrms.jpapairboard.user.service;

import com.prgrms.jpapairboard.user.entity.User;
import com.prgrms.jpapairboard.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class DefaultUserServiceTests {

    @InjectMocks
    private DefaultUserService userService;

    @Mock
    private UserRepository userRepository;

    private User generateTestUser(String name, String email) {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }

    @Nested
    @DisplayName("save 메서드는")
    class Describe_save {

        @Nested
        @DisplayName("인자 값이 null 이면")
        class Context_with_Argument {

            @Test
            @DisplayName("IllegalArgumentException 이 발생한다")
            void It_ThrowsIllegalArgumentException() {
                //given
                //when, then
                assertThatThrownBy(() -> userService.save(null))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("인자 값이 null 이 아니면")
        class Context_with_NotNull {

            @Test
            @DisplayName("Repository 의 save 메서드를 호출한다")
            void It_Call() {
                //given
                final var user = generateTestUser("test", "test@naver.com");

                //when
                userService.save(user);

                //then
                verify(userRepository).save(any(User.class));
            }
        }

    }
}