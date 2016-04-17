package br.com.getjava.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = "spring-data.xml")
public class SpringBootVoteRestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVoteRestauranteApplication.class, args);
	}
}
