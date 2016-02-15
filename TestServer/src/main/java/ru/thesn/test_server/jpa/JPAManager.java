package ru.thesn.test_server.jpa;


import ru.thesn.test_server.jpa.dao.UserDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManager {
    private static JPAManager instance;

    private UserDao userDao;

    private EntityManagerFactory emf;

    public static synchronized JPAManager getInstance(){
        if (instance == null)
            instance = new JPAManager();
        return instance;
    }

    private JPAManager(){

    }


    public synchronized UserDao getUserDao(){
        if (userDao == null)
            userDao = new UserDao();
        return userDao;
    }


    public synchronized EntityManagerFactory getEmf() {
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("MySQL");
        }
        return emf;
    }

    public synchronized void close(){
        emf.close();
        emf = null;
    }

}
