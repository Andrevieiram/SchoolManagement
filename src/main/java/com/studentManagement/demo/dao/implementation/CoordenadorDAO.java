package com.studentManagement.demo.dao.implementation;

import com.studentManagement.demo.database.DB;
import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.entities.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CoordenadorDAO {

    public Coordenador save(Coordenador coordenador) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            if (coordenador.getIdCoord() == null) {
                em.persist(coordenador);
            } else {
                coordenador = em.merge(coordenador);
            }
            em.getTransaction().commit();
            return coordenador;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Coordenador> findByCpf(String cpf) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Coordenador> query = em.createQuery(
                    "SELECT c FROM Coordenador c WHERE c.cpf = :cpf",
                    Coordenador.class);
            query.setParameter("cpf", cpf);
            List<Coordenador> result = query.getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            DB.closeConnection();
        }
    }

    public List<Coordenador> findByNome(String nome) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Coordenador> query = em.createQuery(
                    "SELECT c FROM Coordenador c WHERE LOWER(c.nome) LIKE LOWER(:nome)",
                    Coordenador.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Coordenador> findById(Long id) {
        EntityManager em = DB.openConnection();
        try {
            Coordenador coordenador = em.find(Coordenador.class, id);
            return Optional.ofNullable(coordenador);
        } finally {
            DB.closeConnection();
        }
    }

    public List<Coordenador> findAll() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Coordenador> query = em.createQuery("SELECT c FROM Coordenador c", Coordenador.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public void delete(Long id) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Coordenador coordenador = em.find(Coordenador.class, id);
            if (coordenador != null) {
                em.remove(coordenador);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Coordenador> findByIdCoord(Long idCoord) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Coordenador> query = em.createQuery(
                    "SELECT c FROM Coordenador c WHERE c.idCoord = :idCoord", Coordenador.class);
            query.setParameter("idCoord", idCoord);
            List<Coordenador> result = query.getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Coordenador> findByCursoId(Long cursoId) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Coordenador> query = em.createQuery(
                    "SELECT c FROM Coordenador c WHERE c.curso.idCurso = :cursoId", Coordenador.class);
            query.setParameter("cursoId", cursoId);
            List<Coordenador> result = query.getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            DB.closeConnection();
        }
    }

    public void associarCurso(Coordenador coordenador, Curso curso) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();

            coordenador.setCurso(curso);
            curso.setCoordenador(coordenador);

            em.merge(coordenador);
            em.merge(curso);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public boolean isAssociadoACurso(Long idCoord) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM Coordenador c WHERE c.idCoord = :idCoord AND c.curso IS NOT NULL",
                    Long.class);
            query.setParameter("idCoord", idCoord);
            return query.getSingleResult() > 0;
        } finally {
            DB.closeConnection();
        }
    }
}
