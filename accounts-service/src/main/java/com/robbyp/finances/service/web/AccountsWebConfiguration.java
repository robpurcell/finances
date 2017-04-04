package com.robbyp.finances.service.web;

import com.robbyp.finances.service.backend.AccountsBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountsBackendConfiguration.class})
@ComponentScan
public class AccountsWebConfiguration {


}
