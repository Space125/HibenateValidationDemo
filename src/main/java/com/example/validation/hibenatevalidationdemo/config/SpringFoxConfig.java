package com.example.validation.hibenatevalidationdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.ant;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(ant("/rest/**"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("REST API documentation")
                        .description("Приложение по <a href='https://github.com/Space125/HibenateValidationDemo'>Hibernate Validation Demo</a>")
                        .version("1.0")
                        .build());
    }
}
