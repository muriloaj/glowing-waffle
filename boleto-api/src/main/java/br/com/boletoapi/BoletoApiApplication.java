package br.com.boletoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/rest")
public class BoletoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoletoApiApplication.class, args);
	}
}
