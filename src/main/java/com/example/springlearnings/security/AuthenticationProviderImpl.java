package com.example.springlearnings.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = Objects.nonNull(authentication.getPrincipal()) ? authentication.getName() : "NONE_PROVIDED";
        if (Objects.isNull(username)) {
            throw new BadCredentialsException("Incorrect credentials provided.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return createAuthenticationOnSuccess(authentication, userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Authentication createAuthenticationOnSuccess(Authentication authentication, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), authentication.getCredentials(), authentication.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }
}
