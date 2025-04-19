package com.example.springlearnings.setup;

import com.example.springlearnings.config.EmailConfig;
import com.example.springlearnings.config.SecurityConfig;
import com.example.springlearnings.config.ServiceConfig;
import com.example.springlearnings.config.TxConfig;
import com.example.springlearnings.security.AuthenticationProviderImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@SpringBootTest(classes = {SecurityConfig.class, TxConfig.class, TestConfig.class, ServiceConfig.class, EmailConfig.class})
@ActiveProfiles("test")
public class TestSetup {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    AuthenticationManager authenticationManager;

    @MockitoBean
    AuthenticationProviderImpl authenticationProvider;

    @MockitoBean(name = "mvcHandlerMappingIntrospector")
    HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @MockitoBean
    MongoDatabaseFactory mongoDatabaseFactory;

}
