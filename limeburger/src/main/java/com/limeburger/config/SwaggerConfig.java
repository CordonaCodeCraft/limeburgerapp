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

  public static final String TITLE = "Lime Burger web application";
  public static final String DESCRIPTION =
      "Demo web application for making tons of money from selling burgers and luring out Satoshi Nakamoto from his hideout";
  public static final String VERSION = "1.0";
  public static final String TERMS_OF_SERVICE_URL = "https://limechain.tech/privacy-policy/";
  public static final String LICENSE = "Apache License Version 2.0";
  public static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
  public static final String CONTACT_NAME = "LimeChain";
  public static final String CONTACT_URL = "https://limechain.tech/about/";
  public static final String CONTACT_EMAIL = "hi@limechain.tech";

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

    final Contact contact = new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);

    return new ApiInfo(
        TITLE,
        DESCRIPTION,
        VERSION,
        TERMS_OF_SERVICE_URL,
        contact,
        LICENSE,
        LICENSE_URL,
        new ArrayList<>());
  }
}
