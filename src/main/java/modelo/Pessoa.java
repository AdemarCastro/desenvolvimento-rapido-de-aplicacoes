package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * A classe Pessoa representa uma entidade de pessoa com atributos nome, telefone e email.
 *
 * Anotada com @Entity, esta classe pode ser mapeada para uma tabela em um banco de dados.
 *
 * A classe inclui construtores, getters e setters para seus atributos.
 */
@Entity
public class Pessoa {

    // Atributos da entidade Pessoa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    /**
     * Construtor que inicializa uma nova instância de Pessoa com nome, telefone e email.
     *
     * @param nome o nome da pessoa
     * @param telefone o telefone da pessoa
     * @param email o email da pessoa
     */
    public Pessoa(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    /**
     * Construtor padrão.
     */
    public Pessoa() {}

    /**
     * Obtém o ID da pessoa.
     *
     * @return o ID da pessoa
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da pessoa.
     *
     * @param id o ID a ser definido
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome o nome a ser definido
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o telefone da pessoa.
     *
     * @return o telefone da pessoa
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da pessoa.
     *
     * @param telefone o telefone a ser definido
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o email da pessoa.
     *
     * @return o email da pessoa
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da pessoa.
     *
     * @param email o email a ser definido
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
