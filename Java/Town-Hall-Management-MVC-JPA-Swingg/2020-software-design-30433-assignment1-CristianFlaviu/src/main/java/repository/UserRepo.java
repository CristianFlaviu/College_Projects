package repository;

import entity.House;
import entity.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

public class UserRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void updateUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public User findById(String user_Id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user=em.find(User.class, user_Id);
        em.getTransaction().commit();
        em.close();

        return  user;
    }


    public void removeUser(String user_Id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User refference = em.getReference(User.class, user_Id);
        em.remove(refference);
        em.getTransaction().commit();
        em.close();

    }
    public Set<User> findAllUser() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("from User e", User.class);

        Set<User> requestSet= new HashSet<>(q.getResultList());
        return requestSet;
    }

    public Set<House> findAllHousesbyUserId(String id)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user=em.find(User.class, id);
        em.getTransaction().commit();
        em.close();

        return  user.getHouses();
    }

    public User findUserbEmail(String email)
    {
        Set<User> users=findAllUser();

        for(User user:users)
        {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;

    }

}
