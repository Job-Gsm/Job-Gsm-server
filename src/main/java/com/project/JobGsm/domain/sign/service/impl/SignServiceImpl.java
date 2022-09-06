package com.project.JobGsm.domain.sign.service.impl;

import com.project.JobGsm.domain.sign.dto.request.*;
import com.project.JobGsm.domain.sign.dto.response.UserSignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.domain.sign.service.SignService;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.PasswordNotMatchException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import com.project.JobGsm.global.util.SendEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.project.JobGsm.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignServiceImpl implements SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SendEmailUtil sendEmailUtil;

    /**
     * 회원가입 로직
     * @param signUpDto username, email, password
     * @return userId
     */
    @Override
    @Transactional
    public Long signup(SignUpDto signUpDto) {
        Optional<User> findByEmail = userRepository.findByEmail(signUpDto.getEmail());
        if(findByEmail.isPresent()) {
            throw new DuplicateEmailException(DUPLICATE_EMAIL);
        }
        User user = signUpDto.toEntity(passwordEncoder.encode(signUpDto.getPassword()));
        return userRepository.save(user).getUser_id();
    }

    /**
     * 로그인 로직
     * @param signInDto email, password
     * @return userSigninDto accessToken refreshToken
     */
    @Override
    @Transactional
    public UserSignInResponseDto signin(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException(PASSWORD_NOT_MATCH);
        }
        return new UserSignInResponseDto(createToken(user));
    }

    /**
     * 토큰 생성 로직
     * @param user user
     * @return map accesstoken refreshToken
     */
    @Override
    @Transactional
    public Map<String, String> createToken(User user) {
        Map<String, String> token = new HashMap<>();
        final String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        final String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        user.updateRefreshToken(refreshToken);
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);
        return token;
    }

    /**
     * 회원가입 할 때 이메일 발송 로직
     * @param emailDto email
     * @return authKey
     */
    @Override
    @Transactional
    public String signupSendEmail(EmailDto emailDto) {
        return sendEmailUtil.sendEmailText(emailDto.getEmail());
    }

    /**
     * 비밀번호 잃어버렸을 때 이메일 발송 로직
     * @param emailDto email
     * @return authKey
     */
    @Override
    @Transactional
    public String forgotPasswordSendEmail(EmailDto emailDto) {
        User user = userRepository.findByEmail(emailDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return sendEmailUtil.sendEmailText(emailDto.getEmail());
    }

    /**
     * 인증번호 확인 로직
     * @param checkEmailKeyDto key
     */
    @Override
    public void checkEmailKey(CheckEmailKeyDto checkEmailKeyDto) {
        sendEmailUtil.checkEmailKey(checkEmailKeyDto.getKey());
    }

    /**
     * 비밀번호 변경 로직
     * @param changePasswordDto email, newPassword
     * @return newPassword(encode)
     */
    @Override
    @Transactional
    public String changePassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        String newPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());
        user.updatePassword(newPassword);
        return newPassword;
    }


}
