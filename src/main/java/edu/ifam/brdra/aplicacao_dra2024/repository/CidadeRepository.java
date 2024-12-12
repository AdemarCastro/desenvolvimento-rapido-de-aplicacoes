package edu.ifam.brdra.aplicacao_dra2024.repository;

import edu.ifam.brdra.aplicacao_dra2024.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável pela comunicação com o banco de dados para operações CRUD relacionadas à entidade Cidade.
 * Extende JpaRepository para fornecer métodos básicos de manipulação de dados.
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    /**
     * Recupera uma cidade pelo nome.
     * @param nome Nome da cidade a ser pesquisado.
     * @return A cidade correspondente ao nome fornecido, ou null caso não exista.
     */
    @Query("select c from Cidade c where c.nome = :nome")
    Cidade findByNome(@Param("nome") String nome);
}
