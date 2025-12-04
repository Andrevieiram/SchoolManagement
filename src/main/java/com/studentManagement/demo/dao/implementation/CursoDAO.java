package com.studentManagement.demo.dao.implementation;


import com.studentManagement.demo.database.DB;
import com.studentManagement.demo.entities.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CursoDAO {

    /**
     * Salva ou atualiza um curso
     */
    public Curso save(Curso curso) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            if (curso.getIdCurso() == null) {
                em.persist(curso);
            } else {
                curso = em.merge(curso);
            }
            em.getTransaction().commit();
            return curso;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }

    /**
     * Busca curso por ID
     */
    public Optional<Curso> findById(Long id) {
        EntityManager em = DB.openConnection();
        try {
            Curso curso = em.find(Curso.class, id);
            return Optional.ofNullable(curso);
        } finally {
            DB.closeConnection();
        }
    }


    public List<Curso> findAll() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public void delete(Long id) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                em.remove(curso);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public List<Curso> findByNomeCurso(String nomeCurso) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Curso> query = em.createQuery(
                    "SELECT c FROM Curso c WHERE c.nomeCurso LIKE :nomeCurso", Curso.class);
            query.setParameter("nomeCurso", "%" + nomeCurso + "%");
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public Optional<Curso> findByCoordenadorId(Long idCoordenador) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Curso> query = em.createQuery(
                    "SELECT c FROM Curso c WHERE c.coordenador.idCoord = :idCoordenador", Curso.class);
            query.setParameter("idCoordenador", idCoordenador);
            List<Curso> result = query.getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            DB.closeConnection();
        }
    }


    public boolean temCoordenador(Long idCurso) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM Curso c WHERE c.idCurso = :idCurso AND c.coordenador IS NOT NULL",
                    Long.class);
            query.setParameter("idCurso", idCurso);
            return query.getSingleResult() > 0;
        } finally {
            DB.closeConnection();
        }
    }
}

