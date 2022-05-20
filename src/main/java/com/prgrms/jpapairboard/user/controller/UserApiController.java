package com.prgrms.jpapairboard.user.controller;

import com.prgrms.jpapairboard.user.controller.dto.UserCreateRequest;
import com.prgrms.jpapairboard.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        userService.save(userCreateRequest.toDomain());
    }
}
