package com.prgrms.jpapairboard.user.controller.dto;

import com.prgrms.jpapairboard.user.entity.Hobby;
import com.prgrms.jpapairboard.user.entity.User;

import javax.validation.constraints.*;

public class UserCreateRequest {

    @NotBlank
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @PositiveOrZero
    private final Integer age;

    private final Hobby hobby;

    public UserCreateRequest(String name, String email, int age, Hobby hobby) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.hobby = hobby;
    }

    public User toDomain() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Hobby getHobby() {
        return hobby;
    }
}
