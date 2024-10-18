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
 * O nome da unidade de persistência "Banco01PU" deve estar definido no arquivo `persistence.xml`.
 */
public class TestaPessoa {

    private static void persistir(){
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "Banco01PU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        Pessoa pessoa = new Pessoa("Rogerio2", "3234-5678", "rogerio@email.com");

        entityManager.getTransaction().begin();

        entityManager.persist(pessoa);

        entityManager.getTransaction().commit();

        entityManager.close();

        fabrica.close();

    }

    private static void listar(){
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "Banco01PU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        String consulta = "select p from Pessoa p";

        Query query = entityManager.createQuery(consulta);

        List<Pessoa> pessoas = query.getResultList();

        for(Pessoa p: pessoas){
            System.out.println(p.getNome());
            System.out.println(p.getEmail());
            System.out.println(p.getTelefone());
            System.out.println("***************");
        }
    }

    private static void consultar(){
        // Cria a EntityManagerFactory usando o nome da unidade de persistência "Banco01PU"
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        // Cria o EntityManager
        EntityManager entityManager = fabrica.createEntityManager();

        Pessoa pessoa = entityManager.find(Pessoa.class, 1l);

        System.out.println("Pessoa:" + pessoa.getNome());

        entityManager.close();

        fabrica.close();
    }

    public static void main(String[] args) {
//        persistir();
//        listar();
        consultar();
    }

}
