package edu.ifam.brdra.aplicacao_dra2024.model;

import jakarta.persistence.*;

/**
 * Representa um logradouro, como uma rua ou avenida.
 * Está associado a uma cidade e possui um CEP único.
 */
@Entity
public class Logradouro {

    /**
     * Identificador único do logradouro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código postal (CEP) do logradouro. Deve ser único e não nulo.
     */
    @Column(nullable = false, unique = true)
    private String cep;

    /**
     * Nome do logradouro. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Cidade à qual o logradouro pertence. É obrigatório.
     */
    @ManyToOne(optional = false)
    private Cidade cidade;

    /**
     * Construtor padrão.
     */
    public Logradouro() {
    }

    /**
     * Construtor completo.
     *
     * @param id     Identificador do logradouro.
     * @param cep    Código postal do logradouro.
     * @param nome   Nome do logradouro.
     * @param cidade Cidade associada ao logradouro.
     */
    public Logradouro(Long id, String cep, String nome, Cidade cidade) {
        this.id = id;
        this.cep = cep;
        this.nome = nome;
        this.cidade = cidade;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
