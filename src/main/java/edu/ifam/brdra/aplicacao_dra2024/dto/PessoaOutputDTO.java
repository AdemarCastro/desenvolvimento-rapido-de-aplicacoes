package edu.ifam.brdra.aplicacao_dra2024.dto;

import edu.ifam.brdra.aplicacao_dra2024.model.Pessoa;

/**
 * DTO para saída de dados de uma Pessoa.
 * É usado para enviar dados externos da entidade {@link Pessoa}.
 */
public class PessoaOutputDTO {

    /**
     * Identificador único da pessoa.
     */
    private Long id;

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
     * Construtor que converte uma instância de {@link Pessoa} em {@link PessoaOutputDTO}.
     *
     * @param pessoa Instância de {@link Pessoa}.
     */
    public PessoaOutputDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.cidade = pessoa.getCidade() != null ? pessoa.getCidade().getNome() : null;
    }

    // Getters e setters

    /**
     * Retorna o identificador único da pessoa.
     *
     * @return Identificador único.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da pessoa.
     *
     * @param id Identificador único.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
}
