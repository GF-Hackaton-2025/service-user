package br.com.user.webui.domain.response.usercontroller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.user.webui.description.Descriptions.EMAIL;
import static br.com.user.webui.description.Descriptions.ID_USER;
import static br.com.user.webui.description.Descriptions.NAME_USER;
import static br.com.user.webui.description.Descriptions.USER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponse {

  @Schema(description = ID_USER)
  private String id;
  @Schema(description = NAME_USER)
  private String name;
  @Schema(description = USER)
  private String user;
  @Schema(description = EMAIL)
  private String email;

}
