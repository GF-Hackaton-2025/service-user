package br.com.user.app.services;

import br.com.user.app.entities.User;
import br.com.user.app.exception.BusinessException;
import br.com.user.app.repositories.UserRepository;
import br.com.user.utils.Crypt;
import br.com.user.utils.Dates;
import br.com.user.utils.Jwts;
import br.com.user.utils.Strings;
import br.com.user.webui.domain.dto.ExecuteLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static br.com.user.errors.Errors.INVALID_USER_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;

  public Mono<User> executeLogin(ExecuteLoginDTO executeLoginDTO) {
    return Mono.just(executeLoginDTO)
      .flatMap(this::validateLogin)
      .flatMap(this::createTokenToLogin)
      .doOnError(e -> log.error("Error in process login user", e))
      .onErrorResume(Mono::error);
  }

  private Mono<User> validateLogin(ExecuteLoginDTO executeLoginDTO) {
    return userRepository.findByUser(executeLoginDTO.getUser())
      .filter(u -> Strings.equals(u.getPassword(), Crypt.md5(executeLoginDTO.getPassword()).toUpperCase()))
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

}
