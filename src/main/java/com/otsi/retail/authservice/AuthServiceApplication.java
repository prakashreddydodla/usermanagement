package com.otsi.retail.authservice;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class AuthServiceApplication {
	@Value("${Cognito.aws.region}")
	String REGION;
	@Value("${Cognito.aws.userpool_id}")
	String USERPOOL_ID;
	

	

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	
	@Bean
	public ConfigurableJWTProcessor getConfigurableJWTProcessor() throws MalformedURLException {
		ResourceRetriever resourceRetriever = new DefaultResourceRetriever(2000, 2000);
		URL jwkURL = new URL(
				String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", REGION, USERPOOL_ID));
		JWKSource keySource = new RemoteJWKSet(jwkURL, resourceRetriever);
		ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
		JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256, keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}
	/*
	 * @Bean public Docket swaggerPersonApi10() { return new
	 * Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.basePackage(
	 * "com.otsi.retail.authservice.controller")) .paths(PathSelectors.any())
	 * .build() .apiInfo(new
	 * ApiInfoBuilder().version("1.0").title("user-management").
	 * description("Documentation UserManagement API v1.0").build()); }
	 */
	
	
	
}
