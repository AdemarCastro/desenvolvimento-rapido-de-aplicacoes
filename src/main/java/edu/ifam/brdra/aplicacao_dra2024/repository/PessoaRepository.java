package edu.ifam.brdra.aplicacao_dra2024.repository;

import edu.ifam.brdra.aplicacao_dra2024.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável pela comunicação com o banco de dados para operações CRUD relacionadas à entidade Pessoa.
 * Extende JpaRepository para fornecer métodos básicos de manipulação de dados.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
