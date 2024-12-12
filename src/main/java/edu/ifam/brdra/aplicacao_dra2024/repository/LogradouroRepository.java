package edu.ifam.brdra.aplicacao_dra2024.repository;

import edu.ifam.brdra.aplicacao_dra2024.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável pela comunicação com o banco de dados para operações CRUD relacionadas à entidade Logradouro.
 * Extende JpaRepository para fornecer métodos básicos de manipulação de dados.
 */
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
}
