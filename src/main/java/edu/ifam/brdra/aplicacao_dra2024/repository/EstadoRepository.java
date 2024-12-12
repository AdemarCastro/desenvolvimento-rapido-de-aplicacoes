package edu.ifam.brdra.aplicacao_dra2024.repository;

import edu.ifam.brdra.aplicacao_dra2024.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável pela comunicação com o banco de dados para operações CRUD relacionadas à entidade Estado.
 * Extende JpaRepository para fornecer métodos básicos de manipulação de dados.
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
