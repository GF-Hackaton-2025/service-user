package br.com.user.webui.converters;

import br.com.user.app.entities.User;
import br.com.user.webui.domain.dto.LoginDTO;
import br.com.user.webui.domain.dto.SignupDTO;
import br.com.user.webui.domain.request.authcontroller.LoginRequest;
import br.com.user.webui.domain.request.authcontroller.SignupRequest;
import br.com.user.webui.domain.response.authcontroller.LoginResponse;
import br.com.user.webui.domain.response.authcontroller.SignupResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthConverter {

  public static LoginDTO convertToLoginDTO(LoginRequest request) {
    return LoginDTO.builder()
        .user(request.getUser())
        .password(request.getPassword())
        .build();
  }

  public static LoginResponse convertToLoginResponse(User user) {
    return LoginResponse.builder()
      .type("Bearer")
      .accessToken(user.getAccessToken())
      .build();
  }

  public static SignupDTO convertToSignupDTO(SignupRequest signupRequest) {
    return SignupDTO.builder()
      .name(signupRequest.getName())
      .user(signupRequest.getUser())
      .password(signupRequest.getPassword())
      .email(signupRequest.getEmail())
      .build();
  }

  public static SignupResponse convertToSignupResponse(User user) {
    return SignupResponse.builder()
      .id(String.valueOf(user.getId()))
      .name(user.getName())
      .user(user.getUser())
      .email(user.getEmail())
      .build();
  }

}
