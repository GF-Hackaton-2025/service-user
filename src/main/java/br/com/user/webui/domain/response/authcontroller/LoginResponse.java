package br.com.user.webui.domain.response.authcontroller;

import br.com.user.webui.description.Descriptions;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

  @Schema(description = Descriptions.ACCESS_TOKEN)
  private String accessToken;
  @Schema(description = Descriptions.TYPE)
  private String type;

}
