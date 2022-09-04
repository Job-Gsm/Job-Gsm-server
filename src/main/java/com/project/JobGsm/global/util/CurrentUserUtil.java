package com.project.JobGsm.global.util;

import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.project.JobGsm.global.exception.ErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            email = ((User) principal).getEmail();
        } else {
            email = principal.toString();
        }
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }
}
