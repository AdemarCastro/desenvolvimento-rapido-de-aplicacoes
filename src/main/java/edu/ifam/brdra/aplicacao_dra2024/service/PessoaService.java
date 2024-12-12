package edu.ifam.brdra.aplicacao_dra2024.service;

import edu.ifam.brdra.aplicacao_dra2024.dto.PessoaInputDTO;
import edu.ifam.brdra.aplicacao_dra2024.dto.PessoaOutputDTO;
import edu.ifam.brdra.aplicacao_dra2024.model.Pessoa;
import edu.ifam.brdra.aplicacao_dra2024.repository.CidadeRepository;
import edu.ifam.brdra.aplicacao_dra2024.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pela lógica de negócios relacionada à Pessoa.
 * Contém métodos para realizar operações CRUD e conversão entre objetos de modelo e DTOs.
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    /**
     * Recupera todas as pessoas cadastradas e as converte para o formato DTO.
     * @return Lista de DTOs de pessoas.
     */
    public List<PessoaOutputDTO> list() {
        try {
            List<Pessoa> pessoas = pessoaRepository.findAll();
            List<PessoaOutputDTO> pessoaDTOs = new ArrayList<>();

            for (Pessoa pessoa : pessoas) {
                pessoaDTOs.add(new PessoaOutputDTO(pessoa));
            }

            return pessoaDTOs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cria uma nova pessoa a partir de um DTO de entrada e a persiste no banco de dados.
     * @param pessoaInputDTO DTO com as informações da pessoa a ser criada.
     * @return DTO com as informações da pessoa criada.
     */
    public PessoaOutputDTO create(PessoaInputDTO pessoaInputDTO) {
        try {
            // Converte o DTO para entidade e persiste no banco de dados.
            Pessoa pessoaSalvaNoBD = pessoaRepository.save(pessoaInputDTO.build(cidadeRepository));
            return new PessoaOutputDTO(pessoaSalvaNoBD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera uma pessoa pelo seu ID.
     * @param id Identificador único da pessoa.
     * @return DTO com as informações da pessoa encontrada.
     */
    public PessoaOutputDTO getById(Long id) {
        try {
            Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);
            return new PessoaOutputDTO(possivelPessoa.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove uma pessoa do banco de dados pelo seu ID.
     * @param id Identificador único da pessoa a ser removida.
     */
    public void delete(Long id) {
        try {
            Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);
            pessoaRepository.delete(possivelPessoa.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualiza as informações de uma pessoa existente com base em um DTO de entrada.
     * @param pessoaInputDTO DTO com as novas informações da pessoa.
     * @param id Identificador único da pessoa a ser atualizada.
     * @return DTO com as informações da pessoa atualizada.
     */
    public PessoaOutputDTO update(PessoaInputDTO pessoaInputDTO, Long id) {
        Pessoa pessoa = pessoaInputDTO.build(cidadeRepository);
        Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);

        try {
            Pessoa pessoaUpdade = possivelPessoa.get();
            pessoaUpdade.setNome(pessoa.getNome());
            pessoaUpdade.setEmail(pessoa.getEmail());

            Pessoa pessoaSalvaNoBDpessoa = pessoaRepository.save(pessoaUpdade);
            return new PessoaOutputDTO(pessoaSalvaNoBDpessoa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
