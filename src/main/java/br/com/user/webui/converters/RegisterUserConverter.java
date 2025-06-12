package br.com.user.webui.converters;

import br.com.user.app.entities.User;
import br.com.user.utils.Crypt;
import br.com.user.webui.domain.dto.RegisterUserDTO;
import br.com.user.webui.domain.request.usercontroller.RegisterUserRequest;
import br.com.user.webui.domain.response.usercontroller.RegisterUserResponse;
import reactor.core.publisher.Mono;

public class RegisterUserConverter {

  public static RegisterUserDTO convertToRegisterUserDTO(RegisterUserRequest registerUserRequest) {
    return RegisterUserDTO.builder()
      .name(registerUserRequest.getName())
      .user(registerUserRequest.getUser())
      .password(registerUserRequest.getPassword())
      .email(registerUserRequest.getEmail())
      .build();
  }

  public static Mono<User> convertToRegisterUser(RegisterUserDTO registerUserRequest) {
    return Mono.just(User.builder()
      .name(registerUserRequest.getName())
      .user(registerUserRequest.getUser())
      .password(Crypt.md5(registerUserRequest.getPassword()).toUpperCase())
      .email(registerUserRequest.getEmail())
      .build());
  }

  public static RegisterUserResponse convertToRegisterUserResponse(User user) {
    return RegisterUserResponse.builder()
      .id(String.valueOf(user.getId()))
      .name(user.getName())
      .user(user.getUser())
      .email(user.getEmail())
      .build();
  }

}
