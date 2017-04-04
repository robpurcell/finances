package com.robbyp.finances.commonauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robbyp.finances.common.customers.Address;
import com.robbyp.finances.common.customers.Name;
import com.robbyp.finances.commonauth.CustomerAuthService;
import com.robbyp.finances.commonauth.model.QuerySideCustomer;
import com.robbyp.finances.commonauth.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthControllerIntegrationTestConfiguration.class)
public class AuthControllerIntegrationTest {

  private MockMvc mockMvc;

  @Mock
  TokenService tokenService;

  @Mock
  CustomerAuthService customerAuthService;

  @InjectMocks
  AuthController authController;

  private static ObjectMapper om = new ObjectMapper();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
  }

  @Test
  public void shouldLogin() throws Exception {
    when(customerAuthService.findByEmailAndPassword("my@email.com", "my_password")).thenReturn(new QuerySideCustomer("id", new Name("test", "test"), "my@email.com", "my_password", "ssn", "", new Address(), null));
    when(customerAuthService.findByEmailAndPassword("not_my@email.com", "not_my_password")).thenThrow(new EmptyResultDataAccessException(1));

    when(tokenService.allocateToken(om.writeValueAsString(new User("my@email.com")))).thenReturn(new DefaultToken("key", System.currentTimeMillis(), ""));

    mockMvc.perform(post("/api/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\" : \"my@email.com\", \"password\" : \"my_password\"}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(result -> {
              assertTrue(result.getResponse().getContentAsString().contains("id"));
              assertTrue(result.getResponse().getContentAsString().contains("my@email.com"));
              assertTrue(result.getResponse().getContentAsString().contains("my_password"));
            });

    mockMvc.perform(post("/api/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\" : \"not_my@email.com\", \"password\" : \"not_my_password\"}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }
}
