package br.com.user.webui.controller;

import br.com.user.app.services.AuthService;
import br.com.user.webui.converters.AuthConverter;
import br.com.user.webui.domain.request.authcontroller.LoginRequest;
import br.com.user.webui.domain.request.authcontroller.SignupRequest;
import br.com.user.webui.domain.response.ErrorResponse;
import br.com.user.webui.domain.response.authcontroller.LoginResponse;
import br.com.user.webui.domain.response.authcontroller.SignupResponse;
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
@RequestMapping(value = "/auth")
@Tag(name = "Login", description = "Operations related to login")
@ApiResponse(responseCode = "401", description = "Unauthorized",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "422", description = "Unprocessable Entity",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "400", description = "Bad Request",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  @ResponseStatus(CREATED)
  @Operation(summary = "Login", description = "Login")
  @ApiResponse(responseCode = "200", description = "Get token successfuly")
  public Mono<LoginResponse> login(
    @RequestBody @Valid
    final LoginRequest loginRequest) {

    return Mono.just(AuthConverter.convertToLoginDTO(loginRequest))
      .flatMap(authService::login)
      .map(AuthConverter::convertToLoginResponse)
      .contextWrite(Context.of(REQUEST_ID, UUID.randomUUID().toString()));
  }

  @PostMapping("/signup")
  @ResponseStatus(CREATED)
  @Operation(summary = "Create user", description = "Create user")
  @ApiResponse(responseCode = "201", description = "User completed successfuly")
  public Mono<SignupResponse> signup(
    @RequestBody @Valid
    final SignupRequest signupRequest) {

    return Mono.just(AuthConverter.convertToSignupDTO(signupRequest))
      .flatMap(authService::signup)
      .map(AuthConverter::convertToSignupResponse)
      .contextWrite(Context.of(REQUEST_ID, UUID.randomUUID().toString()));
  }

}
