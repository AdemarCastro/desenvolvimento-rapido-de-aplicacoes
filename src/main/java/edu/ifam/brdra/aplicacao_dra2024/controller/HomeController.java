// Controller principal para rotas de teste da aplicação
package edu.ifam.brdra.aplicacao_dra2024.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável por controlar as rotas principais da aplicação.
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    /**
     * Endpoint raiz da aplicação.
     * @return Mensagem de teste para validar o funcionamento do Spring Boot.
     */
    @GetMapping
    public String iniciar() {
        return "Testando minha aplicação spring boot com localhost 8080";
    }
}