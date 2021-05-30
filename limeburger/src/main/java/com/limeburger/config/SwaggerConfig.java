package com.limeburger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/")
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {

    String title = "Lime Burger web application";
    String description =
        "Demo web application for making tons of money from selling burgers and luring out Satoshi Nakamoto from his hideout";
    String version = "1.0";
    String termsOfServiceUrl = "https://limechain.tech/privacy-policy/";
    String license = "Apache License Version 2.0";
    String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";

    String contactName = "LimeChain";
    String contactUrl = "https://limechain.tech/about/";
    String contactEmail = "hi@limechain.tech";

    Contact contact = new Contact(contactName, contactUrl, contactEmail);

    return new ApiInfo(
        title,
        description,
        version,
        termsOfServiceUrl,
        contact,
        license,
        licenseUrl,
        new ArrayList<>());
  }
}
