package br.com.user.webui.controller;

import br.com.user.app.services.UserService;
import br.com.user.webui.converters.RegisterUserConverter;
import br.com.user.webui.domain.request.usercontroller.RegisterUserRequest;
import br.com.user.webui.domain.response.ErrorResponse;
import br.com.user.webui.domain.response.usercontroller.RegisterUserResponse;
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
@RequestMapping(value = "/v1/user/x")
@Tag(name = "User", description = "Operations related to user")
@ApiResponse(responseCode = "401", description = "Unauthorized",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "422", description = "Unprocessable Entity",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "400", description = "Bad Request",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error",
  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Create user", description = "Create user")
  @ApiResponse(responseCode = "201", description = "User completed successfuly")
  public Mono<RegisterUserResponse> registerUser(
    @RequestBody @Valid
    final RegisterUserRequest registerUserRequest) {

    return Mono.just(RegisterUserConverter.convertToRegisterUserDTO(registerUserRequest))
      .flatMap(userService::registerUser)
      .map(RegisterUserConverter::convertToRegisterUserResponse)
      .contextWrite(Context.of(REQUEST_ID, UUID.randomUUID().toString()));
  }

}
