package br.com.user.app.services;

import br.com.user.app.entities.User;
import br.com.user.app.exception.BusinessException;
import br.com.user.app.repositories.UserRepository;
import br.com.user.webui.domain.dto.LoginDTO;
import br.com.user.webui.domain.dto.SignupDTO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthServiceTest {

  @InjectMocks
  private AuthService authService;
  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void login_Success() {
    LoginDTO loginDTO = LoginDTO.builder()
      .user("user")
      .password("123")
      .build();

    User user = User.builder()
      .id(new ObjectId())
      .name("User")
      .user("user")
      .password("202cb962ac59075b964b07152d234b70")
      .build();

    when(userRepository.findByUser(any()))
      .thenReturn(Mono.just(user));

    StepVerifier.create(authService.login(loginDTO))
      .expectNextMatches(u -> u.getUser().equals("user"))
      .verifyComplete();
  }

  @Test
  void login_InvalidPassword() {
    LoginDTO loginDTO = LoginDTO.builder()
      .user("user")
      .password("wrong")
      .build();

    User user = User.builder()
      .id(new ObjectId())
      .name("User")
      .user("user")
      .password("202cb962ac59075b964b07152d234b70")
      .build();

    when(userRepository.findByUser(any()))
      .thenReturn(Mono.just(user));

    StepVerifier.create(authService.login(loginDTO))
      .expectError(BusinessException.class)
      .verify();
  }

  @Test
  void signup_UserAlreadyExists() {
    SignupDTO signupDTO = SignupDTO.builder()
      .user("user")
      .build();

    when(userRepository.findByUser(any()))
      .thenReturn(Mono.just(new User()));

    StepVerifier.create(authService.signup(signupDTO))
      .expectError(BusinessException.class)
      .verify();
  }

  @Test
  void signup_Success() {
    SignupDTO signupDTO = SignupDTO.builder()
      .user("user")
      .name("User")
      .password("123456")
      .email("user@email.com")
      .build();

    when(userRepository.findByUser(any()))
      .thenReturn(Mono.empty());
    when(userRepository.save(any()))
      .thenReturn(Mono.just(User.builder().user("user").build()));

    StepVerifier.create(authService.signup(signupDTO))
      .expectNextMatches(u -> u.getUser().equals("user"))
      .verifyComplete();
  }
}

