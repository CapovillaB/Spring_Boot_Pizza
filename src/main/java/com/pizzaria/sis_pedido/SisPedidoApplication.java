package com.pizzaria.sis_pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableJdbcHttpSession
public class SisPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisPedidoApplication.class, args);
	}

}
