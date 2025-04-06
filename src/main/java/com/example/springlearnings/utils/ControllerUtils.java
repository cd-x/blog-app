package com.example.springlearnings.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerUtils {
    public static String getUsernameFromSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
