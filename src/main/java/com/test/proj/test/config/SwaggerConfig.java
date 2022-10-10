package com.test.proj.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration for Open API documentation using Swagger
 */
@Configuration
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Test API")
                .description("Test API for user registration")
                .license("LICENSE")
                .licenseUrl("https://github.com/syeds-git/test-api/blob/main/LICENSE")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("Syed Ali", "https://github.com/syeds-git?tab=repositories", "user@email.com"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
