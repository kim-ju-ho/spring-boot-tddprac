package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.dto.user.SignupRequestDto;
import com.sparta.deliveryapi.models.user.User;
import com.sparta.deliveryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup-user")
    public User userSignup(@RequestBody SignupRequestDto userDto){
        return userService.userSignup(userDto);

    }
    @PostMapping("/user/signup-owner")
    public User ownerSignup(@RequestBody SignupRequestDto userDto){
        return userService.ownerSignup(userDto);

    }
}
