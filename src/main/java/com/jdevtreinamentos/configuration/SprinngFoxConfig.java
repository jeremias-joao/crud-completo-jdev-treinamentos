package com.jdevtreinamentos.configuration;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SprinngFoxConfig {
	
	//http://localhost:8080/swagger-ui/index.html
	@Bean
	public Docket swaggerEditoraApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("com.jdevtreinamentos"))
				.build().apiInfo(informacaoApi());
	}
	
	
	private ApiInfo informacaoApi() {
		return new ApiInfoBuilder()
				.title("JDEV Treimanentos")
				.description("API Cadastro User")
				.version("1.0.0")
				.build();
	}
}
