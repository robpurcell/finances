package com.robbyp.finances.commonauth.controller;

import com.robbyp.finances.commonauth.AuthConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AuthConfiguration.class)
@EnableAutoConfiguration
public class AuthControllerIntegrationTestConfiguration {
}
