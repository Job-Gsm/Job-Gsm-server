package com.project.JobGsm.domain.sign.service.impl;

import com.project.JobGsm.domain.sign.User;
import com.project.JobGsm.domain.sign.dto.request.CheckEmailKeyDto;
import com.project.JobGsm.domain.sign.dto.request.SignInDto;
import com.project.JobGsm.domain.sign.dto.request.SignUpDto;
import com.project.JobGsm.domain.sign.dto.request.SignUpEmailDto;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.sign.repository.UserRepository;
import com.project.JobGsm.domain.sign.service.SignService;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.PasswordNotMatchException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import com.project.JobGsm.global.util.SendEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.project.JobGsm.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignServiceImpl implements SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SendEmailUtil sendEmailUtil;

    @Override
    public Long signup(SignUpDto signUpDto) {
        Optional<User> findByEmail = userRepository.findByEmail(signUpDto.getEmail());
        if(findByEmail.isPresent()) {
            throw new DuplicateEmailException(DUPLICATE_EMAIL);
        }
        User user = signUpDto.toEntity(passwordEncoder.encode(signUpDto.getPassword()));
        return userRepository.save(user).getUser_id();
    }

    @Override
    public UserSignInResponseDto signin(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
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

    @Override
    public String signupEmail(SignUpEmailDto signUpEmailDto) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(88888) + 11111);
        sendEmailUtil.sendEmailText(signUpEmailDto.getEmail(), authKey);
        return authKey;
    }

    @Override
    public void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto) {
        sendEmailUtil.checkEmailKey(checkEmailKeyDto.getKey());
    }
}
