package br.com.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
      .group("service-user")
      .pathsToMatch("/v1/**")
      .build();
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Service User API")
        .version("1.0"));
  }
}
