package edu.ifam.brdra.aplicacao_dra2024.controller;

import edu.ifam.brdra.aplicacao_dra2024.model.Estado;
import edu.ifam.brdra.aplicacao_dra2024.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por gerenciar os endpoints relacionados a Estado.
 */
@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    /**
     * Recupera todos os estados cadastrados.
     * @return Lista de estados ou status HTTP 404 caso nenhum seja encontrado.
     */
    @GetMapping
    public ResponseEntity<List<Estado>> list() {
        List<Estado> estados = estadoRepository.findAll();

        if (!estados.isEmpty()) {
            return new ResponseEntity<>(estados, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cria um novo estado.
     * @param estado Entidade Estado recebida no corpo da requisição.
     * @return Estado criado ou erro HTTP 400 caso a criação falhe.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> create(@RequestBody Estado estado) {
        try {
            Estado estadoSalvoNoBD = estadoRepository.save(estado);
            return new ResponseEntity<>(estadoSalvoNoBD, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Recupera um estado pelo seu ID.
     * @param id Identificador único do estado.
     * @return Estado correspondente ou erro HTTP 404 caso não encontrado.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> getById(@PathVariable Long id) {
        Optional<Estado> possivelEstado = estadoRepository.findById(id);

        if (possivelEstado.isPresent()) {
            return new ResponseEntity<>(possivelEstado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Remove um estado pelo seu ID.
     * @param id Identificador único do estado.
     * @return Status HTTP 204 em caso de sucesso ou erro correspondente.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Estado> possivelEstado = estadoRepository.findById(id);

        if (possivelEstado.isPresent()) {
            try {
                estadoRepository.delete(possivelEstado.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Atualiza as informações de um estado existente.
     * @param estado Objeto contendo os novos dados.
     * @param id Identificador único do estado a ser atualizado.
     * @return Estado atualizado ou erro correspondente.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> update(@RequestBody Estado estado, @PathVariable Long id) {
        Optional<Estado> possivelEstado = estadoRepository.findById(id);

        if (possivelEstado.isPresent()) {
            try {
                Estado estadoAtualizado = possivelEstado.get();
                estadoAtualizado.setNome(estado.getNome());
                estadoAtualizado.setIbge(estado.getIbge());

                Estado estadoSalvoNoBD = estadoRepository.save(estadoAtualizado);
                return new ResponseEntity<>(estadoSalvoNoBD, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
