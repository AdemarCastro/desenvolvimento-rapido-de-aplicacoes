package edu.ifam.brdra.aplicacao_dra2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável pela execução da aplicação Spring Boot.
 *
 * Esta classe inicializa a aplicação Spring Boot e inicia o contexto de execução
 * da aplicação, carregando todas as configurações necessárias e disponibilizando
 * os serviços e endpoints configurados.
 *
 * A anotação @SpringBootApplication é uma combinação das anotações @Configuration,
 * @EnableAutoConfiguration e @ComponentScan, o que indica que a classe é um ponto
 * de entrada para a aplicação Spring Boot.
 */
@SpringBootApplication
public class AplicacaoDra2024Application {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args Argumentos passados para a aplicação, como parâmetros de linha
	 *             de comando. Não utilizados diretamente neste exemplo.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AplicacaoDra2024Application.class, args);
	}
}
