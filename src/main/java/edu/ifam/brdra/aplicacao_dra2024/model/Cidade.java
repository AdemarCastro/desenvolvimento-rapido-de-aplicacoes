package edu.ifam.brdra.aplicacao_dra2024.model;

import jakarta.persistence.*;

/**
 * Representa uma cidade.
 * Contém informações básicas como nome e estado.
 */
@Entity
public class Cidade {

    /**
     * Identificador único da cidade.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da cidade. Deve ser único e não nulo.
     */
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     * Estado ao qual a cidade pertence. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String estado;

    /**
     * Construtor padrão.
     */
    public Cidade() {
    }

    /**
     * Construtor completo.
     *
     * @param id     Identificador da cidade.
     * @param nome   Nome da cidade.
     * @param estado Estado ao qual a cidade pertence.
     */
    public Cidade(Long id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
