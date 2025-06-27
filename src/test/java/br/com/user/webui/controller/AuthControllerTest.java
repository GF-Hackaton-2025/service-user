package br.com.user.webui.controller;

import br.com.user.app.entities.User;
import br.com.user.app.services.AuthService;
import br.com.user.webui.domain.request.authcontroller.LoginRequest;
import br.com.user.webui.domain.request.authcontroller.SignupRequest;
import br.com.user.webui.domain.response.authcontroller.LoginResponse;
import br.com.user.webui.domain.response.authcontroller.SignupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

  @InjectMocks
  private AuthController authController;
  @Mock
  private AuthService authService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void login_ReturnsLoginResponse() {
    when(authService.login(any()))
      .thenReturn(Mono.just(User.builder().user("x").accessToken("Bearer").build()));

    Mono<LoginResponse> response = authController.login(new LoginRequest());
    StepVerifier.create(response)
      .expectNextMatches(e -> !e.getAccessToken().isEmpty())
      .verifyComplete();

    assertNotNull(response);
  }

  @Test
  void signup_ReturnsSignupResponse() {
    when(authService.signup(any()))
      .thenReturn(Mono.just(User.builder().user("xpto").password("x").build()));

    var request = SignupRequest.builder()
      .name("Test User")
      .user("testuser")
      .email("123@gmail.com")
      .build();

    Mono<SignupResponse> response = authController.signup(request);

    StepVerifier.create(response)
      .expectNextMatches(u -> u.getUser().equals("xpto"))
      .verifyComplete();

    assertNotNull(response);
  }
}

