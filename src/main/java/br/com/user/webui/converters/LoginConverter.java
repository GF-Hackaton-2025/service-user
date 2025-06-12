package br.com.user.webui.converters;

import br.com.user.app.entities.User;
import br.com.user.webui.domain.dto.ExecuteLoginDTO;
import br.com.user.webui.domain.request.logincontroller.ExecuteLoginRequest;
import br.com.user.webui.domain.response.logincontroller.ExecuteLoginResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoginConverter {

  public ExecuteLoginDTO convertToExecuteLoginDTO(ExecuteLoginRequest request) {
    return ExecuteLoginDTO.builder()
        .user(request.getUser())
        .password(request.getPassword())
        .build();
  }

  public ExecuteLoginResponse convertToExecuteLoginResponse(User user) {
    return ExecuteLoginResponse.builder()
      .type("Bearer")
      .accessToken(user.getAccessToken())
      .build();
  }
}
