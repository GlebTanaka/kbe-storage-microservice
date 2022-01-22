package de.htwberlin.f4.storagemicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "External Product Storage API",
                "Retrieve and store additional Product Information",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Gleb & Linda",
                        "https://github.com/GlebTanaka/kbe-storage-microservice",
                        "gleb5655@gmail.com"),
                "API License",
                "https://github.com/GlebTanaka/kbe-storage-microservice",
                Collections.emptyList()
        );
    }
}
