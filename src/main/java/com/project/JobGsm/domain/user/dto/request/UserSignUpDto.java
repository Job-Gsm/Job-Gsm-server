package com.project.JobGsm.domain.user.dto.request;

import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.domain.user.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDto {

    private String email;
    private String password;
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
