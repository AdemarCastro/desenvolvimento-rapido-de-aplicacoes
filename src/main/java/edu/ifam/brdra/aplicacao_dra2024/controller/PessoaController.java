// Controller para gerenciamento de Pessoas
package edu.ifam.brdra.aplicacao_dra2024.controller;

import edu.ifam.brdra.aplicacao_dra2024.dto.PessoaInputDTO;
import edu.ifam.brdra.aplicacao_dra2024.dto.PessoaOutputDTO;
import edu.ifam.brdra.aplicacao_dra2024.model.Pessoa;
import edu.ifam.brdra.aplicacao_dra2024.repository.CidadeRepository;
import edu.ifam.brdra.aplicacao_dra2024.repository.PessoaRepository;
import edu.ifam.brdra.aplicacao_dra2024.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por gerenciar os endpoints relacionados a Pessoa.
 */
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private PessoaService pessoaService;

    /**
     * Lista todas as pessoas cadastradas no sistema.
     * @return Lista de objetos PessoaOutputDTO ou erro HTTP 404 caso nenhuma pessoa seja encontrada.
     */
    @GetMapping
    public ResponseEntity<List<PessoaOutputDTO>> list() {
        try {
            // Obtém a lista de pessoas formatada para DTO
            List<PessoaOutputDTO> pessoasDTO = pessoaService.list();
            return new ResponseEntity<>(pessoasDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cria um novo registro de pessoa.
     * @param pessoaInputDTO Dados recebidos no corpo da requisição.
     * @return Pessoa criada ou erro HTTP 400 caso a criação falhe.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> create(@RequestBody PessoaInputDTO pessoaInputDTO) {
        try {
            // Cria uma nova pessoa utilizando o serviço
            PessoaOutputDTO pessoaOutputDTO = pessoaService.create(pessoaInputDTO);
            return new ResponseEntity<>(pessoaOutputDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Atualiza as informações de uma pessoa existente.
     * @param pessoa Objeto contendo os novos dados da pessoa.
     * @param id Identificador único da pessoa a ser atualizada.
     * @return Pessoa atualizada ou um erro correspondente (HTTP 400 ou HTTP 404).
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id) {

        // Busca a pessoa pelo ID fornecido.
        Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);

        // Verifica se a pessoa existe no banco de dados.
        if (possivelPessoa.isPresent()) {

            try {
                // Recupera a pessoa encontrada.
                Pessoa pessoaUpdade = possivelPessoa.get();

                // Atualiza os atributos da pessoa com os novos valores recebidos.
                pessoaUpdade.setNome(pessoa.getNome());
                pessoaUpdade.setEmail(pessoa.getEmail());

                // Salva as alterações no banco de dados.
                Pessoa pessoaSalvaNoBDpessoa = pessoaRepository.save(pessoaUpdade);

                // Retorna a pessoa atualizada com status HTTP 200 (OK).
                return new ResponseEntity<>(pessoaSalvaNoBDpessoa, HttpStatus.OK);

            } catch (Exception e) {
                // Retorna erro HTTP 400 (BAD REQUEST) caso ocorra algum problema durante a atualização.
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            // Retorna erro HTTP 404 (NOT FOUND) caso a pessoa não seja encontrada pelo ID.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
