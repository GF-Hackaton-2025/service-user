package br.com.user.app.services;

import br.com.user.app.entities.User;
import br.com.user.app.exception.BusinessException;
import br.com.user.app.repositories.UserRepository;
import br.com.user.utils.Crypt;
import br.com.user.utils.Dates;
import br.com.user.utils.Jwts;
import br.com.user.utils.Strings;
import br.com.user.webui.domain.dto.LoginDTO;
import br.com.user.webui.domain.dto.SignupDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static br.com.user.errors.Errors.INVALID_USER_PASSWORD;
import static br.com.user.errors.Errors.USER_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  public Mono<User> login(LoginDTO loginDTO) {
    return Mono.just(loginDTO)
      .flatMap(this::validateLogin)
      .flatMap(this::createTokenToLogin)
      .doOnError(e -> log.error("Error in process login user", e))
      .onErrorResume(Mono::error);
  }

  public Mono<User> signup(SignupDTO signupDTO) {
    return Mono.just(signupDTO)
      .flatMap(dto -> userRepository.findByUser(dto.getUser()))
      .hasElement()
      .flatMap(existsUser -> {
        if (existsUser)
          return Mono.error(new BusinessException(USER_ALREADY_EXISTS));
        return Mono.just(signupDTO)
          .flatMap(this::convertToRegisterUser)
          .flatMap(userRepository::save);
      })
      .doOnError(e -> log.error("Error in process register user", e))
      .onErrorResume(Mono::error);
  }

  private Mono<User> validateLogin(LoginDTO loginDTO) {
    return userRepository.findByUser(loginDTO.getUser())
      .filter(u -> Strings.equals(u.getPassword(), Crypt.md5(loginDTO.getPassword()).toUpperCase()))
      .switchIfEmpty(Mono.error(new BusinessException(INVALID_USER_PASSWORD)));
  }

  private Mono<User> createTokenToLogin(User user) {
    var token = Jwts.create(LocalDateTime.now().plusMinutes(5),
      "userId", String.valueOf(user.getId()),
      "name", user.getName(),
      "token_type", "Bearer",
      "created_in", Dates.format(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
    );
    user.setAccessToken(token);
    return Mono.just(user);
  }

  public Mono<User> convertToRegisterUser(SignupDTO registerUserRequest) {
    return Mono.just(User.builder()
      .name(registerUserRequest.getName())
      .user(registerUserRequest.getUser())
      .password(Crypt.md5(registerUserRequest.getPassword()).toUpperCase())
      .email(registerUserRequest.getEmail())
      .build());
  }

}
