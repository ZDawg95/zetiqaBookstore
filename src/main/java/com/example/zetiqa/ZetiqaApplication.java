package com.example.zetiqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
public class ZetiqaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZetiqaApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_12)
				.select()
				.paths(PathSelectors.ant("/zetiqa/*"))
				.apis(RequestHandlerSelectors.basePackage("com.example.zetiqa"))
				.build()
				.apiInfo(apiZetiqaData());
	}

	private ApiInfo apiZetiqaData(){
		return new ApiInfo(
				"Zetiqa Bookstore Application",
				"This is a TEST REST API Application for ZETIQA (Zachary + Etiqa) BOOKSTORE, a demo bookstore for ETIQA's technical assessment!",
				"1.0",
				"Zetiqa",
				new Contact("Zachary","www.zetiqa.com","zachchoong21@gmail.com"),
				"Zetiqa License",
				"www.ZetiqaLicensing.com",
				Collections.emptyList()
		);
	}

}
