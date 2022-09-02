package com.project.JobGsm.domain.sign.controller;

import com.project.JobGsm.domain.sign.dto.request.RefreshTokenDto;
import com.project.JobGsm.domain.sign.service.RefreshTokenService;
import com.project.JobGsm.global.response.ResponseService;
import com.project.JobGsm.global.response.result.SingleResult;
import com.project.JobGsm.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final RefreshTokenService refreshTokenService;

    @PutMapping("refreshToken")
    public SingleResult<Map<String, String>> refreshToken(HttpServletRequest request, @Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        Map<String, String> result = refreshTokenService.refreshToken(jwtTokenProvider.getRefreshToken(request), refreshTokenDto);
        return responseService.getSingleResult(result);
    }
}
