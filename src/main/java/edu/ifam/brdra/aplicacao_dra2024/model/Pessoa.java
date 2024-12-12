package edu.ifam.brdra.aplicacao_dra2024.model;

import jakarta.persistence.*;

/**
 * Representa uma pessoa.
 * Contém informações como nome, e-mail e cidade de residência.
 */
@Entity
public class Pessoa {

    /**
     * Identificador único da pessoa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da pessoa. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * E-mail da pessoa. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Cidade onde a pessoa reside. É opcional.
     */
    @ManyToOne
    private Cidade cidade;

    /**
     * Construtor padrão.
     */
    public Pessoa() {
    }

    /**
     * Construtor completo.
     *
     * @param id     Identificador da pessoa.
     * @param nome   Nome da pessoa.
     * @param email  E-mail da pessoa.
     * @param cidade Cidade de residência.
     */
    public Pessoa(Long id, String nome, String email, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    /**
     * Construtor sem cidade.
     *
     * @param id    Identificador da pessoa.
     * @param nome  Nome da pessoa.
     * @param email E-mail da pessoa.
     */
    public Pessoa(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
