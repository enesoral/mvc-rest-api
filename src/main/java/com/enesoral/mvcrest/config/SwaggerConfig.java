package com.enesoral.mvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String CUSTOMER_CONTROLLER_TAG = "Customer Controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData())
                .tags(new Tag(CUSTOMER_CONTROLLER_TAG, "Produces operations of customer."));
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Enes Oral", "http://enesoral.com/", "info@enesoral.com");

        return new ApiInfo(
                "Rest API",
                "Rest API for Fruit Shop",
                "1.0",
                "Terms of Services.......",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
