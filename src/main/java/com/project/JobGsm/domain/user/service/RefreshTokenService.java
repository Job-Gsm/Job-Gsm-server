package com.project.JobGsm.domain.user.service;

import com.project.JobGsm.domain.user.dto.request.RefreshTokenDto;

import java.util.Map;

public interface RefreshTokenService {

    Map<String, String> refreshToken(String refreshToken, RefreshTokenDto refreshTokenDto);
}
