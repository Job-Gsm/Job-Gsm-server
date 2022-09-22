package com.project.JobGsm.domain.user.service.impl;

import com.project.JobGsm.domain.board.service.S3Service;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.ProfileResponseDto;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.domain.user.service.UserService;
import com.project.JobGsm.global.exception.exceptions.DuplicateEmailException;
import com.project.JobGsm.global.exception.exceptions.PasswordNotMatchException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import com.project.JobGsm.global.util.CurrentUserUtil;
import com.project.JobGsm.global.util.ResponseDtoUtil;
import com.project.JobGsm.global.util.SendEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.project.JobGsm.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SendEmailUtil sendEmailUtil;
    private final CurrentUserUtil currentUserUtil;
    private final S3Service s3Service;

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
    public SignInResponseDto signin(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException(PASSWORD_NOT_MATCH);
        }
        return new SignInResponseDto(createToken(user));
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
     * 이메일 인증번호 발송 로직
     * @param emailDto email
     * @return authKey
     */
    @Override
    @Transactional
    public String sendEmail(EmailDto emailDto) {
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

    /**
     * 디스코드, 깃허브, 경력, 닉네임 업데이트 로직
     * @param userInformationDto email, username, githup, discord, career
     */
    @Override
    @Transactional
    public void updateUserInformation(UserInformationDto userInformationDto) {

        User user = userRepository.findByEmail(userInformationDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        user.updateUserInformation(userInformationDto.getUsername(), userInformationDto.getGithub(), userInformationDto.getDiscord());

    }

    /**
     * 전공 선택 로직
     * @param selectMajorDto email, major
     */
    @Override
    @Transactional
    public void selectMajor(SelectMajorDto selectMajorDto) {

        User user = userRepository.findByEmail(selectMajorDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        user.updateMajor(selectMajorDto.getMajor(), selectMajorDto.getCareer());

        System.out.println("selectMajorDto.getMajor() = " + selectMajorDto.getMajor());
        System.out.println("user = " + user);
    }

    /**
     * 프로필 사진 업데이트 하는 로직
     * @param file file
     */
    @Override
    @Transactional
    public void uploadProfileImage(MultipartFile file) {

        User user = currentUserUtil.getCurrentUser();
        String url = s3Service.upload(file, "profile_image/");
        user.updateUrl(url);

    }

    /**
     * 인증객체로 내 프로필 로직
     * @return name, email, hithub, major, career, career
     */
    @Override
    public ProfileResponseDto findByUserId(Long user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return ResponseDtoUtil.map(user, ProfileResponseDto.class);

    }

    /**
     * 프로필 상세보기 로직
     * @return name, email, hithub, major, career, career
     */
    @Override
    @Transactional
    public ProfileResponseDto currentUser() {

        User user = currentUserUtil.getCurrentUser();

        return ResponseDtoUtil.map(user, ProfileResponseDto.class);

    }


}
