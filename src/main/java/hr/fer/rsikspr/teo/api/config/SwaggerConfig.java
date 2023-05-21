package hr.fer.rsikspr.teo.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
//@EnableSwagger2WebMvc
public class SwaggerConfig {
  
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("hr.fer.rsikspr.teo")) // Update with your base package
        .paths(PathSelectors.any())
        .build();
  }
}



/*
@Configuration
//@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
//@EnableWebMvc

public class SwaggerConfig {
    private static final String TITLE = "Infobip RSIKSPR API";
    private static final String DESCRIPTION = "Descripcion API Infobip RSIKSPR";
    private static final String BASE_PACKAGE = "hr.fer.rsikspr.teo.api";
    private static final String VERSION = "v1";
    /*
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    
    
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build()
                .forCodeGeneration(true)
                .apiInfo(new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION).build());
    }
    
    
}
*/