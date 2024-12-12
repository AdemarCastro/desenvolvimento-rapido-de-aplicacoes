// Controller para gerenciar entidades Logradouro
package edu.ifam.brdra.aplicacao_dra2024.controller;

import edu.ifam.brdra.aplicacao_dra2024.model.Logradouro;
import edu.ifam.brdra.aplicacao_dra2024.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por gerenciar os endpoints relacionados a Logradouro.
 */
@RestController
@RequestMapping("/api/logradouro")
public class LogradouroController {

    @Autowired
    private LogradouroRepository logradouroRepository;

    /**
     * Recupera todos os logradouros cadastrados.
     * @return Lista de logradouros ou status HTTP 404 caso nenhum seja encontrado.
     */
    @GetMapping
    public ResponseEntity<List<Logradouro>> list() {
        // Obtém todos os logradouros do banco de dados
        List<Logradouro> logradouros = logradouroRepository.findAll();

        // Retorna a lista ou status de não encontrado
        if (!logradouros.isEmpty()) {
            return new ResponseEntity<>(logradouros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cria um novo logradouro.
     * @param logradouro Entidade Logradouro recebida no corpo da requisição.
     * @return Logradouro criado ou erro HTTP 400 caso a criação falhe.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Logradouro> create(@RequestBody Logradouro logradouro) {
        try {
            // Salva o logradouro no banco de dados
            Logradouro logradouroSalvoNoBD = logradouroRepository.save(logradouro);

            // Retorna o logradouro criado com status HTTP 201
            return new ResponseEntity<>(logradouroSalvoNoBD, HttpStatus.CREATED);
        } catch (Exception e) {
            // Retorna erro caso algo falhe
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Recupera um logradouro pelo seu ID.
     * @param id Identificador único do logradouro.
     * @return Logradouro correspondente ou erro HTTP 404 caso não encontrado.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Logradouro> getById(@PathVariable Long id) {
        // Busca o logradouro pelo ID
        Optional<Logradouro> possivelLogradouro = logradouroRepository.findById(id);
        if (possivelLogradouro.isPresent()) {
            return new ResponseEntity<>(possivelLogradouro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Remove um logradouro pelo seu ID.
     * @param id Identificador único do logradouro.
     * @return Status HTTP 204 em caso de sucesso ou erro correspondente.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Busca o logradouro pelo ID
        Optional<Logradouro> possivelLogradouro = logradouroRepository.findById(id);

        if (possivelLogradouro.isPresent()) {
            try {
                // Remove o logradouro do banco de dados
                logradouroRepository.delete(possivelLogradouro.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Atualiza as informações de um logradouro existente.
     * @param logradouro Objeto contendo os novos dados.
     * @param id Identificador único do logradouro a ser atualizado.
     * @return Logradouro atualizado ou erro correspondente.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Logradouro> update(@RequestBody Logradouro logradouro, @PathVariable Long id) {
        // Busca o logradouro pelo ID
        Optional<Logradouro> possivelLogradouro = logradouroRepository.findById(id);

        if (possivelLogradouro.isPresent()) {
            try {
                // Atualiza as informações do logradouro
                Logradouro logradouroAtualizado = possivelLogradouro.get();
                logradouroAtualizado.setCep(logradouro.getCep());
                logradouroAtualizado.setNome(logradouro.getNome());
                logradouroAtualizado.setCidade(logradouro.getCidade());

                // Salva as mudanças no banco de dados
                Logradouro logradouroSalvoNoBD = logradouroRepository.save(logradouroAtualizado);

                return new ResponseEntity<>(logradouroSalvoNoBD, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}