package teste;

import modelo.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * A classe TestaPessoa demonstra o uso básico da JPA (Java Persistence API) para
 * persistir uma entidade Pessoa no banco de dados.
 *
 * Esta classe cria uma instância de Pessoa, inicia uma transação, persiste a
 * entidade no banco de dados e, em seguida, faz o commit da transação.
 *
 * O nome da unidade de persistência "SistemaPU" deve estar definido no arquivo `persistence.xml`.
 */
public class TestaPessoa {

    /**
     * Método principal para persistir uma entidade Pessoa no banco de dados.
     *
     *
     */
    private static void persistir() {
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        // Cria uma nova instância de Pessoa
        Pessoa pessoa = new Pessoa("00000000011", "Rogerio", "3234-5678", "rogerio@email.com");

        // Inicia uma transação
        entityManager.getTransaction().begin();

        // Persiste a entidade Pessoa
        entityManager.persist(pessoa);

        // Realiza o commit da transação
        entityManager.getTransaction().commit();

        // Fecha o EntityManager e a EntityManagerFactory
        entityManager.close();
        fabrica.close();
    }

    /**
     * Método principal para listar todas as entidades Pessoa do banco de dados.
     * Realiza uma consulta para obter todas as instâncias de Pessoa e imprime
     * suas informações no console.
     */
    private static void listar() {
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        // Consulta todas as instâncias de Pessoa
        String consulta = "select p from Pessoa p";
        Query query = entityManager.createQuery(consulta);

        // Obtém a lista de resultados
        List<Pessoa> pessoas = query.getResultList();

        // Imprime as informações das pessoas
        for (Pessoa p : pessoas) {
            System.out.println(p.getId());
            System.out.println(p.getNome());
            System.out.println(p.getEmail());
            System.out.println(p.getTelefone());
            System.out.println("***************");
        }

        // Fecha o EntityManager e a EntityManagerFactory
        entityManager.close();
        fabrica.close();
    }

    /**
     * Método principal para consultar uma entidade Pessoa específica do banco de dados.
     * Obtém a instância de Pessoa com o ID fornecido e imprime seu nome no console.
     */
    private static void consultar() {
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        // Consulta a Pessoa com o ID 1
        Pessoa pessoa = entityManager.find(Pessoa.class, 1L);
        System.out.println("Pessoa:" + pessoa.getNome());

        // Fecha o EntityManager e a EntityManagerFactory
        entityManager.close();
        fabrica.close();
    }

    /**
     * Método principal para remover uma entidade Pessoa do banco de dados.
     * Obtém a instância de Pessoa com o ID fornecido e a remove do banco de dados.
     */
    private static void remover() {
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        // Consulta a Pessoa com o ID 1
        Pessoa pessoa = entityManager.find(Pessoa.class, 1L);

        // Remove a entidade Pessoa
        entityManager.remove(pessoa);

        // Fecha o EntityManager e a EntityManagerFactory
        entityManager.close();
        fabrica.close();
    }

    private static void alterar(){
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        entityManager.getTransaction().begin();

        Pessoa pessoa = entityManager.find(Pessoa.class, 2l);

        pessoa.setNome("Nome alterado.");

        entityManager.getTransaction().commit();

        entityManager.close();
        fabrica.close();
    }

    private static void mesclar(){
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "SistemaPU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(2l);
        pessoa.setNome("Alterado com o mesclar.");

        entityManager.getTransaction().begin();

        entityManager.merge(pessoa);

        entityManager.getTransaction().commit();

        entityManager.close();
        fabrica.close();
    }

    /**
     * Método principal para executar o programa.
     * Comente ou descomente as linhas para chamar os métodos desejados.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
//         persistir();
//         consultar();
//         remover();
//        mesclar();
        listar();
    }
}
