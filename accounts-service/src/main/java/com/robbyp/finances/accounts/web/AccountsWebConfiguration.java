package com.robbyp.finances.accounts.web;

import com.robbyp.finances.accounts.service.AccountsBackendConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountsBackendConfiguration.class})
@ComponentScan
public class AccountsWebConfiguration {


}
