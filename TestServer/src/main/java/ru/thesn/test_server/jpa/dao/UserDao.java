package ru.thesn.test_server.jpa.dao;


import ru.thesn.test_server.jpa.JPAManager;
import ru.thesn.test_server.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao {
    private EntityManager em;

    private EntityManager getEM() {
        if (em == null){
            em = JPAManager.getInstance().getEmf().createEntityManager();
        }
        return em;
    }

    public void close(){
        if (em != null) em.close();
        em = null;
    }


    public User getUser(String login) {
        EntityTransaction tx = getEM().getTransaction();
        tx.begin();
        User balance = getEM().find(User.class, login);
        tx.commit();
        return balance;
    }

    public void save(User user) {
        EntityTransaction tx = getEM().getTransaction();
        tx.begin();
        getEM().persist(user);
        tx.commit();
    }

}
