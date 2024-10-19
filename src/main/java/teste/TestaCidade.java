package teste;

import modelo.Cidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TestaCidade {

    private static void persistir(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        Cidade cidade = new Cidade("Manaus", "123456", "Amazonas");

        entityManager.getTransaction().begin();

        entityManager.persist(cidade);

        entityManager.getTransaction().commit();

        entityManager.close();
        fabrica.close();

    }

    private static void listar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        String consulta = "select c from Cidade c";
        Query query = entityManager.createQuery(consulta);

        List<Cidade> cidades = query.getResultList();

        for (Cidade c : cidades) {
            System.out.println(c.getId());
            System.out.println(c.getNome());
            System.out.println(c.getIbge());
            System.out.println(c.getEstado());
            System.out.println("***************");
        }

        entityManager.close();
        fabrica.close();
    }

    private static void consultar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        Cidade cidade = entityManager.find(Cidade.class, 1L);
        System.out.println("Cidade:" + cidade.getNome());

        entityManager.close();
        fabrica.close();
    }

    private static void remover(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        Cidade cidade = entityManager.find(Cidade.class, 1L);

        // Remove a entidade Pessoa
        entityManager.remove(cidade);

        entityManager.close();
        fabrica.close();
    }

    private static void alterar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        entityManager.getTransaction().begin();

        Cidade cidade = entityManager.find(Cidade.class, 2l);

        cidade.setNome("Nome alterado.");

        entityManager.getTransaction().commit();

        entityManager.close();
        fabrica.close();
    }

    private static void mesclar(){
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager entityManager = fabrica.createEntityManager();

        Cidade cidade = new Cidade();
        cidade.setId(2l);
        cidade.setNome("Alterado com o mesclar.");

        entityManager.getTransaction().begin();

        entityManager.merge(cidade);

        entityManager.getTransaction().commit();

        entityManager.close();
        fabrica.close();
    }

    public static void main(String[] args) {
//        persistir();
//        consultar();
//        remover();
//        mesclar();
//        listar();
    }

}
