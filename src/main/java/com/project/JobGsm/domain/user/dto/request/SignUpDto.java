package com.project.JobGsm.domain.user.dto.request;

import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+@gsm.hs.kr$", message = "학교계정을 입력해주세요")
    @NotBlank
    private String email;

    @Size(min = 5, max = 20)
    @NotBlank
    private String password;
    @NotBlank
    private String username;

    public User toEntity(String password) {
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .role(Collections.singletonList(Role.ROLE_USER))
                .build();
    }

}
