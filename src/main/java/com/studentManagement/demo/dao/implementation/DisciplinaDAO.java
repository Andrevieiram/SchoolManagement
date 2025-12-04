package com.studentManagement.demo.dao.implementation;

import com.studentManagement.demo.database.DB;
import com.studentManagement.demo.entities.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DisciplinaDAO {


    public Disciplina save(Disciplina disciplina) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            if (disciplina.getIdDisc() == null) {
                em.persist(disciplina);
            } else {
                disciplina = em.merge(disciplina);
            }
            em.getTransaction().commit();
            return disciplina;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public Optional<Disciplina> findById(Long id) {
        EntityManager em = DB.openConnection();
        try {
            Disciplina disciplina = em.find(Disciplina.class, id);
            return Optional.ofNullable(disciplina);
        } finally {
            DB.closeConnection();
        }
    }


    public List<Disciplina> findAll() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM Disciplina d", Disciplina.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public void delete(Long id) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Disciplina disciplina = em.find(Disciplina.class, id);
            if (disciplina != null) {
                em.remove(disciplina);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public List<Disciplina> findByNome(String nome) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                    "SELECT d FROM Disciplina d WHERE d.nome LIKE :nome", Disciplina.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public List<Disciplina> findByArea(String area) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                    "SELECT d FROM Disciplina d WHERE d.area = :area", Disciplina.class);
            query.setParameter("area", area);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public List<Disciplina> findByCargaHrMinima(Integer cargaHrMinima) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                    "SELECT d FROM Disciplina d WHERE d.cargaHr >= :cargaHrMinima", Disciplina.class);
            query.setParameter("cargaHrMinima", cargaHrMinima);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public List<Disciplina> findByProfessorId(Long idProfessor) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                    "SELECT pd.disciplina FROM ProfessorDisciplina pd WHERE pd.professor.idProf = :idProfessor",
                    Disciplina.class);
            query.setParameter("idProfessor", idProfessor);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public Long countByArea(String area) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(d) FROM Disciplina d WHERE d.area = :area", Long.class);
            query.setParameter("area", area);
            return query.getSingleResult();
        } finally {
            DB.closeConnection();
        }
    }
}
