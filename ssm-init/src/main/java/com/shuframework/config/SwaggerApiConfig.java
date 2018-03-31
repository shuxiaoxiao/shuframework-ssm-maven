package com.shuframework.config;

import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.EnableWebMvc;  
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;  

import springfox.documentation.builders.ApiInfoBuilder;  
import springfox.documentation.builders.PathSelectors;  
import springfox.documentation.builders.RequestHandlerSelectors;  
import springfox.documentation.service.ApiInfo;  
import springfox.documentation.service.Contact;  
import springfox.documentation.spi.DocumentationType;  
import springfox.documentation.spring.web.plugins.Docket;  
import springfox.documentation.swagger2.annotations.EnableSwagger2;  
  
/**  
* 描述：swagger2配置类 
* 
*/  
//@EnableWebMvc
//@ComponentScan(basePackages={"com.shuframework"}) //需要扫描的包路径 
@EnableSwagger2
@Configuration
public class SwaggerApiConfig{  
	//访问地址http://localhost:8080/ssm-init/swagger-ui.html
	
    @Bean    
    public Docket createRestApi() {    
        return new Docket(DocumentationType.SWAGGER_2)    
                .apiInfo(apiInfo())    
                .select()    
                .apis(RequestHandlerSelectors.basePackage("com.shuframework"))    
                .paths(PathSelectors.any())    
                .build();    
    }    
    
    private ApiInfo apiInfo() {    
        return new ApiInfoBuilder()    
                .title("ssm-api接口")    
                .termsOfServiceUrl("")    
//                .contact(new Contact("我本码农", "", "15851503917@163.com"))    
                .version("v1.0")    
                .build();    
    }    
}  
