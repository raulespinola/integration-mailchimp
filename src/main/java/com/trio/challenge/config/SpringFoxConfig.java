package com.trio.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo());
    }

    ApiInfo getApiInfo(){
        return new ApiInfo("Service Integration Trio Challenge Technical Design",
                "the main goal of this proyect are: Testing my coding capabilities\n" +
                        "- Integrate two services: a mock api service and mailchimp api.",
                "V1.0",
                "",
                new Contact(
                        "Raul",
                        "",
                        "raulespinola@gmail.com"
                ), "", "", new ArrayList<>());
    }
}
