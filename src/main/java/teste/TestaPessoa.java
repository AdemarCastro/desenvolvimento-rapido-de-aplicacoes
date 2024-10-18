package teste;

import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaPessoa {
    public static void main(String[] args) {
       EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("Banco01PU");
        EntityManager entityManager = fabrica.createEntityManager();
        Pessoa pessoa = new Pessoa("Rogerio", "3234-3543", "rogerio@email.com");
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();
        entityManager.close();
        fabrica.close();
    }
}
