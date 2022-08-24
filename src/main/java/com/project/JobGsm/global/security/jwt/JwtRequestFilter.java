package com.project.JobGsm.global.security.jwt;

import com.project.JobGsm.global.exception.exceptions.InvalidTokenException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.JobGsm.global.exception.ErrorCode.INVALID_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");

        if(accessToken != null) {
            if (!jwtTokenProvider.getTokenType(accessToken).equals("accessToken")) {
                throw new InvalidTokenException(INVALID_TOKEN);
            }
            String email = accessTokenExractEmail(accessToken);
            registerUserInfoSecurityContext(email, request);
        }
        filterChain.doFilter(request, response);
    }

    public String accessTokenExractEmail(String accessToken) {
        try {
            return jwtTokenProvider.getUserEmail(accessToken);
        } catch (JwtException e) {
            throw new RuntimeException();
        }
    }

    private void registerUserInfoSecurityContext(String userEmail, HttpServletRequest request) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (NullPointerException e) {
            throw new RuntimeException();
        }
    }
}
