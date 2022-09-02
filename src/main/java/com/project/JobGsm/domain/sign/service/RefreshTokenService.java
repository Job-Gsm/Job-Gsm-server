package com.project.JobGsm.domain.sign.service;

import com.project.JobGsm.domain.sign.dto.request.RefreshTokenDto;

import java.util.Map;

public interface RefreshTokenService {

    Map<String, String> refreshToken(String refreshToken, RefreshTokenDto refreshTokenDto);
}
