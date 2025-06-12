package br.com.user.app.services;

import br.com.user.app.entities.User;
import br.com.user.app.exception.BusinessException;
import br.com.user.app.repositories.UserRepository;
import br.com.user.webui.converters.RegisterUserConverter;
import br.com.user.webui.domain.dto.RegisterUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.user.errors.Errors.USER_ALREADY_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Mono<User> registerUser(RegisterUserDTO registerUserDTO) {
    return Mono.just(registerUserDTO)
      .flatMap(dto -> userRepository.findByUser(dto.getUser()))
      .hasElement()
      .flatMap(existsUser -> {
        if (existsUser)
          return Mono.error(new BusinessException(USER_ALREADY_EXISTS));
        return Mono.just(registerUserDTO)
          .flatMap(RegisterUserConverter::convertToRegisterUser)
          .flatMap(userRepository::save);
      })
      .doOnError(e -> log.error("Error in process register user", e))
      .onErrorResume(Mono::error);
  }

}
