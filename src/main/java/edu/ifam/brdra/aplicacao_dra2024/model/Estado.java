package edu.ifam.brdra.aplicacao_dra2024.model;

import jakarta.persistence.*;

/**
 * Representa um estado brasileiro.
 * Contém informações como o código IBGE e o nome.
 */
@Entity
public class Estado {

    /**
     * Identificador único do estado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código IBGE do estado. Deve ser único e não nulo.
     */
    @Column(nullable = false, unique = true)
    private String ibge;

    /**
     * Nome do estado. Deve ser único e não nulo.
     */
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     * Construtor padrão.
     */
    public Estado() {
    }

    /**
     * Construtor completo.
     *
     * @param id   Identificador do estado.
     * @param ibge Código IBGE do estado.
     * @param nome Nome do estado.
     */
    public Estado(Long id, String ibge, String nome) {
        this.id = id;
        this.ibge = ibge;
        this.nome = nome;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
