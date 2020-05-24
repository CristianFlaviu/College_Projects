package repository;

import Utils.DateFormatter;
import entity.House;
import entity.Request;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

public class RequestRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    public Request findById(String req_Id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request request=em.find(Request.class, req_Id);
        em.getTransaction().commit();
        em.close();

        return  request;
    }
    public Set<Request> findAllRequestByUserId(String user_Id) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user=em.find(User.class, user_Id);
        em.getTransaction().commit();
        em.close();

        return  new HashSet<>(user.getRequests());
    }
    public Set<Request> findAllRequest()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("from Request e", Request.class);

        Set<Request> requestSet= new HashSet<>(q.getResultList());
        return requestSet;
    }
    public void removeRequest(String requestId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request refference = em.getReference(Request.class, requestId);
        em.remove(refference);
        em.getTransaction().commit();
        em.close();

    }

    public void updateRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(request);
        em.getTransaction().commit();
        em.close();
    }
    public List<Request> findAllRequestSortByDate()
    {
        List<Request> lista = new ArrayList<>(findAllRequest());
        List<Request> sortedList = lista.stream()
                .sorted(Comparator.comparing(Request::getDate))
                .collect(Collectors.toList());
        return sortedList;
    }

    public List<Request> findAllRequestSortedByDocumentType()
    {
        List<Request> lista = new ArrayList<>(findAllRequest());
        List<Request> sortedList = lista.stream()
                .sorted(Comparator.comparing(b->b.getDocument().getType()))
                .collect(Collectors.toList());
        return sortedList;
    }

    public List<Request> findAllRequestFileteredByDate(int year, int month)
    {
        List<Request> lista = new ArrayList<>(findAllRequest());
        List<Request> sortedList = lista.stream()
                .filter(b-> DateFormatter.getYear(b.getDate())==year)
                .filter(b-> DateFormatter.getMonth(b.getDate())==month)
                .collect(Collectors.toList());
        return sortedList;
    }



}
