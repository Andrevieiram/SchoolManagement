package com.studentManagement.demo.database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DB {
    public static  EntityManagerFactory emf;
    public static EntityManager em;

    public static EntityManager openConnection(){
        emf = Persistence.createEntityManagerFactory("SchoolManagement");
        em  = emf.createEntityManager();

        return em;
    }

    public static void closeConnection(){
        emf.close();
        em.close();
    }
}
