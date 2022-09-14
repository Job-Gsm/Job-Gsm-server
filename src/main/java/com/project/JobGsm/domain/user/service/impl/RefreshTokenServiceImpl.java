package com.project.JobGsm.domain.user.service.impl;

import com.project.JobGsm.domain.user.dto.request.RefreshTokenDto;
import com.project.JobGsm.domain.user.repository.UserRepository;
import com.project.JobGsm.domain.user.service.RefreshTokenService;
import com.project.JobGsm.domain.user.User;
import com.project.JobGsm.global.exception.ErrorCode;
import com.project.JobGsm.global.exception.exceptions.InvalidTokenException;
import com.project.JobGsm.global.exception.exceptions.RefreshTokenExpirationException;
import com.project.JobGsm.global.exception.exceptions.UserNotFoundException;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public Map<String, String> refreshToken(String refreshToken, RefreshTokenDto refreshTokenDto) {

        User user = userRepository.findByEmail(refreshTokenDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        Map<String, String> token = new HashMap<>();

        if (!jwtTokenProvider.isExpired(refreshToken) && user.getRefreshToken().equals(refreshToken)) {

            if (user.getRefreshToken() == null) {
                throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
            }

            String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
            token.put("accessToken", accessToken);

            return token;
        }

        throw new RefreshTokenExpirationException(ErrorCode.REFRESH_TOKEN_EXPIRATION);
    }
}
