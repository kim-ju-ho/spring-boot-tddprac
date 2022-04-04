package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.dto.user.SignupRequestDto;
import com.sparta.deliveryapi.models.user.User;
import com.sparta.deliveryapi.models.user.UserRoleEnum;
import com.sparta.deliveryapi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final static String USER_="ROLE_USER";


    public User userSignup(SignupRequestDto userDto) {
        String userName = userDto.getUsername();
        String password = userDto.getPassword();
        String passwordEncode= passwordEncoder.encode(password);
        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User(userName, passwordEncode, role);
       return userRepository.save(user);
    }

    public User ownerSignup(SignupRequestDto userDto) {
        String userName = userDto.getUsername();
        String password = userDto.getPassword();
        String passwordEncode= passwordEncoder.encode(password);
        UserRoleEnum role = UserRoleEnum.OWNER;
        User user = new User(userName, passwordEncode, role);
        return userRepository.save(user);
    }
}
