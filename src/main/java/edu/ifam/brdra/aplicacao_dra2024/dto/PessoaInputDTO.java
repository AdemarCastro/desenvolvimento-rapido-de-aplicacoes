package edu.ifam.brdra.aplicacao_dra2024.dto;

import edu.ifam.brdra.aplicacao_dra2024.model.Pessoa;
import edu.ifam.brdra.aplicacao_dra2024.repository.CidadeRepository;

/**
 * DTO para entrada de dados de uma Pessoa.
 * É usado para receber dados externos e criar uma instância do modelo {@link Pessoa}.
 */
public class PessoaInputDTO {

    /**
     * Nome da pessoa.
     */
    private String nome;

    /**
     * E-mail da pessoa.
     */
    private String email;

    /**
     * Nome da cidade onde a pessoa reside.
     */
    private String cidade;

    /**
     * Construtor padrão.
     */
    public PessoaInputDTO() {
    }

    // Getters e setters

    /**
     * Retorna o nome da pessoa.
     *
     * @return Nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome Nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o e-mail da pessoa.
     *
     * @return E-mail da pessoa.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail da pessoa.
     *
     * @param email E-mail da pessoa.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o nome da cidade onde a pessoa reside.
     *
     * @return Nome da cidade.
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define o nome da cidade onde a pessoa reside.
     *
     * @param cidade Nome da cidade.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Constrói uma instância de {@link Pessoa} a partir dos dados fornecidos.
     *
     * @param cidadeRepository Repositório de cidades para buscar a entidade {@link edu.ifam.brdra.aplicacao_dra2024.model.Cidade}.
     * @return Instância de {@link Pessoa}.
     */
    public Pessoa build(CidadeRepository cidadeRepository) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setEmail(this.email);
        pessoa.setCidade(cidadeRepository.findByNome(this.cidade));
        return pessoa;
    }
}
