package repository;

import entity.Document;
import entity.House;
import entity.Request;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewDocument(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(document);
        em.getTransaction().commit();
        em.close();
    }
    public Document findById(String document_id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document document=em.find(Document.class, document_id);
        em.getTransaction().commit();
        em.close();
        return  document;
    }


    public Set<Document> findAllDocuments() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("from Document e", Document.class);


        List<Document> list= q.getResultList();
        Set<Document> a=new HashSet<>();

        for(Document i:list)
            a.add(i);
        return a;
    }

    public void removeDocument(String documentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document refference = em.getReference(Document.class, documentId);
        em.remove(refference);
        em.getTransaction().commit();
        em.close();

    }

    public void updateDocument(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(document);
        em.getTransaction().commit();
        em.close();
    }

//    public Set<Document> findAllDocumentsByUserID(String id) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        User user=em.find(Document.class, User);
//        em.getTransaction().commit();
//        em.close();
//        return  user.ge;
//    }

}
