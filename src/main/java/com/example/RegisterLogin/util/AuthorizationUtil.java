package com.example.RegisterLogin.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
public class AuthorizationUtil {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public void checkAuthorizationToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken == null) {
            throw new RuntimeException("Missing or invalid Authorization token");
        }

//        String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (!jwtTokenUtil.isJwtValid(jwtToken)) {
            throw new RuntimeException("Invalid JWT token");
        }
    }
}
