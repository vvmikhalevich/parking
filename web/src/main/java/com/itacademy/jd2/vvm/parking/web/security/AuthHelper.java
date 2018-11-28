package com.itacademy.jd2.vvm.parking.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    private AuthHelper() {
    }

    public static Integer getLoggedUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (!(auth instanceof ExtendedUsernamePasswordAuthenticationToken)) {
            return null;
        }


        ExtendedUsernamePasswordAuthenticationToken extendedAuth = (ExtendedUsernamePasswordAuthenticationToken) auth;
        return extendedAuth.getId();
    }
}
