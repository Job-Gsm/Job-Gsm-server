package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.dto.request.*;
import com.project.JobGsm.domain.user.dto.response.ProfileResponseDto;
import com.project.JobGsm.domain.user.dto.response.SignInResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {

    Long signup(SignUpDto signUpDto);
    SignInResponseDto signin(SignInDto signInDto);
    Map<String, String> createToken(User user);
    void changePassword(ChangePasswordDto changePasswordDto);
    void updateUserInformation(UserInformationDto userInformationDto);
    void selectMajor(SelectMajorDto selectMajorDto);
    void uploadProfileImage(MultipartFile file);
    ProfileResponseDto currentUser();
    ProfileResponseDto findByUserId(Long user_id);

}
