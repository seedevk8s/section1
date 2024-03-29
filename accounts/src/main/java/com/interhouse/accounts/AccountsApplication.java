package com.interhouse.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts 마이크로서비스 REST API 문서",
		description = "LesBank Accounts 마이크로서비스 REST API 문서",
		version = "v1",
		contact = @Contact(
			name = "Chu Hojin",
			email = "tutor@kosta.or.kr",
			url = "https://kostaswedu.co.kr"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://kostaswedu.co.kr"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "LesBank Accounts 마이크로서비스 REST API 문서",
		url = "https://kostaswedu.co.kr/swagger-ui.html"
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
