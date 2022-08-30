package com.project.JobGsm.domain.user.service.impl;

import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.dto.request.UserSignInDto;
import com.project.JobGsm.domain.user.dto.request.UserSignUpDto;
import com.project.JobGsm.domain.user.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.PasswordNotMatchException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.project.JobGsm.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long signup(UserSignUpDto userSignUpDto) {
        Optional<User> findByEmail = userRepository.findByEmail(userSignUpDto.getEmail());
        if(findByEmail.isPresent()) {
            throw new DuplicateEmailException(DUPLICATE_EMAIL);
        }
        User user = userSignUpDto.toEntity(passwordEncoder.encode(userSignUpDto.getPassword()));
        return userRepository.save(user).getUser_id();
    }

    @Override
    public UserSignInResponseDto signin(UserSignInDto userSignInDto) {
        User user = userRepository.findByEmail(userSignInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if(passwordEncoder.matches(userSignInDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException(PASSWORD_NOT_MATCH);
        }

        return new UserSignInResponseDto(createToken(user));
    }

    @Override
    public Map<String, String> createToken(User user) {
        Map<String, String> token = new HashMap<>();
        final String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        final String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        user.updateRefreshToken(refreshToken);
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);
        return token;
    }


}
