package edu.ifam.brdra.aplicacao_dra2024.controller;

import edu.ifam.brdra.aplicacao_dra2024.model.Cidade;
import edu.ifam.brdra.aplicacao_dra2024.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe controladora para gerenciar os endpoints relacionados à entidade Cidade.
 * Ela define os métodos para listagem, criação, atualização, deleção e consulta individual de cidades.
 */
@RestController
@RequestMapping("/api/cidade") // Define a URL base para os recursos de cidade
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository; // Injeta o repositório para acesso ao banco de dados

    /**
     * Recupera todas as cidades cadastradas.
     * @return Lista de cidades ou status HTTP 404 caso nenhum registro seja encontrado.
     */
    @GetMapping
    public ResponseEntity<List<Cidade>> list() {
        List<Cidade> cidades = cidadeRepository.findAll();

        if (!cidades.isEmpty()) {
            return new ResponseEntity<>(cidades, HttpStatus.OK); // Retorna a lista com status 200 (OK)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna status 404 se não houver cidades
        }
    }

    /**
     * Cria uma nova cidade.
     * @param cidade Entidade Cidade recebida no corpo da requisição.
     * @return A cidade criada com status HTTP 201 (Criado) ou erro HTTP 400 em caso de falha.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> create(@RequestBody Cidade cidade) {
        try {
            Cidade cidadeSalvaNoBD = cidadeRepository.save(cidade);
            return new ResponseEntity<>(cidadeSalvaNoBD, HttpStatus.CREATED); // Retorna a cidade criada com status 201
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna erro 400 caso a criação falhe
        }
    }

    /**
     * Recupera uma cidade específica pelo seu ID.
     * @param id Identificador único da cidade.
     * @return A cidade correspondente ou erro HTTP 404 caso a cidade não seja encontrada.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> getById(@PathVariable Long id) {
        Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

        if (possivelCidade.isPresent()) {
            return new ResponseEntity<>(possivelCidade.get(), HttpStatus.OK); // Retorna a cidade com status 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro 404 caso a cidade não seja encontrada
        }
    }

    /**
     * Remove uma cidade pelo seu ID.
     * @param id Identificador único da cidade.
     * @return Status HTTP 204 (Sem conteúdo) em caso de sucesso ou erro correspondente.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

        if (possivelCidade.isPresent()) {
            try {
                cidadeRepository.delete(possivelCidade.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna status 204 após exclusão bem-sucedida
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro 500 em caso de falha interna
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro 404 caso a cidade não seja encontrada
        }
    }

    /**
     * Atualiza as informações de uma cidade existente.
     * @param cidade Objeto contendo os novos dados da cidade.
     * @param id Identificador único da cidade a ser atualizada.
     * @return A cidade atualizada ou erro HTTP 404 se a cidade não for encontrada.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> update(@RequestBody Cidade cidade, @PathVariable Long id) {
        Optional<Cidade> possivelCidade = cidadeRepository.findById(id);

        if (possivelCidade.isPresent()) {
            try {
                Cidade cidadeUpdate = possivelCidade.get();
                cidadeUpdate.setNome(cidade.getNome()); // Atualiza o nome da cidade
                cidadeUpdate.setEstado(cidade.getEstado()); // Atualiza o estado da cidade

                Cidade cidadeAtualizadaNoBD = cidadeRepository.save(cidadeUpdate);
                return new ResponseEntity<>(cidadeAtualizadaNoBD, HttpStatus.OK); // Retorna a cidade atualizada com status 200
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna erro 400 caso a atualização falhe
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna erro 404 caso a cidade não exista
        }
    }
}
