package br.com.user.webui.controller;

import br.com.user.app.services.LoginService;
import br.com.user.webui.converters.LoginConverter;
import br.com.user.webui.domain.request.logincontroller.ExecuteLoginRequest;
import br.com.user.webui.domain.response.ErrorResponse;
import br.com.user.webui.domain.response.logincontroller.ExecuteLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

import static br.com.user.constants.Constants.REQUEST_ID;
import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/login")
@Tag(name = "Login", description = "Operations related to login")
@ApiResponse(responseCode = "401", description = "Unauthorized",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "422", description = "Unprocessable Entity",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "400", description = "Bad Request",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
public class LoginController {

  private final LoginService loginService;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Login", description = "Login")
  @ApiResponse(responseCode = "200", description = "Get token successfuly")
  public Mono<ExecuteLoginResponse> executeLogin(
    @RequestBody @Valid
    final ExecuteLoginRequest loginRequest) {

    return Mono.just(LoginConverter.convertToExecuteLoginDTO(loginRequest))
      .flatMap(loginService::executeLogin)
      .map(LoginConverter::convertToExecuteLoginResponse)
      .contextWrite(Context.of(REQUEST_ID, UUID.randomUUID().toString()));
  }
}
