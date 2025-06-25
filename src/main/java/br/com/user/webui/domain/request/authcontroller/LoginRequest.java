package br.com.user.webui.domain.request.authcontroller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.user.errors.Errors.FIELD_REQUIRED;
import static br.com.user.webui.description.Descriptions.PASSWORD;
import static br.com.user.webui.description.Descriptions.USER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  @Schema(description = USER)
  @NotBlank(message = FIELD_REQUIRED)
  private String user;
  @Schema(description = PASSWORD)
  @NotBlank(message = FIELD_REQUIRED)
  private String password;

}
