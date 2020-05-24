package repository;

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

public class HouseRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewHouse(House house) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(house);
        em.getTransaction().commit();
        em.close();
    }
    public House findById(String Id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        House user=em.find(House.class, Id);
        em.getTransaction().commit();
        em.close();

        return  user;
    }

    public void removeHouse(String houseID) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        House refference = em.getReference(House.class, houseID);
        em.remove(refference);
        em.getTransaction().commit();
        em.close();

    }

    public Set<House> findAllHouses() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("from House e", House.class);


        List<House> list= q.getResultList();
        Set<House> a=new HashSet<>();

        for(House i:list)
            a.add(i);
        return a;
    }

    public Set<House> findAllRequest() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("from House e", House.class);

        Set<House> requestSet= new HashSet<>(q.getResultList());
        return requestSet;
    }

    public Set<House> findAllHouseByUserId(String Id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user=em.find(User.class, Id);
        em.getTransaction().commit();
        em.close();

        return  user.getHouses();
    }





}
