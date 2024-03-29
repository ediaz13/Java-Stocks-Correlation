package com.soma.stocks.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(metaData());
    }
    
    
    List<VendorExtension> list = new ArrayList<VendorExtension>();
 
    
    
    private ApiInfo metaData() {
         return new ApiInfo(
                 "Spring Boot REST API",
                 "Spring Boot REST API to calculate Pearson's Correlation Between two Stocks",
                 "1.0",
                 "Terms of service",
                 new Contact("Emanuel Diaz", "https://financequotes-api.com/#singlestock", "diazemanuel27@gmail.com"),
                "Apache License Version 2.0",
                 "https://www.apache.org/licenses/LICENSE-2.0", list);
    }
}
