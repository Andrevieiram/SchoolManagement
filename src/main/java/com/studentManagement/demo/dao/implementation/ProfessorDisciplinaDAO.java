package com.studentManagement.demo.dao.implementation;

import com.studentManagement.demo.database.DB;
import com.studentManagement.demo.entities.ProfessorDisciplina;
import com.studentManagement.demo.entities.utils.ProfessorDisciplinaId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class ProfessorDisciplinaDAO {

    public ProfessorDisciplina save(ProfessorDisciplina professorDisciplina) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();

            // Verifica se a entidade já existe
            boolean exists = em.find(ProfessorDisciplina.class, professorDisciplina.getId()) != null;

            if (exists) {
                professorDisciplina = em.merge(professorDisciplina);
            } else {
                em.persist(professorDisciplina);
            }

            em.getTransaction().commit();
            return professorDisciplina;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public Optional<ProfessorDisciplina> findById(ProfessorDisciplinaId id) {
        EntityManager em = DB.openConnection();
        try {
            ProfessorDisciplina professorDisciplina = em.find(ProfessorDisciplina.class, id);
            return Optional.ofNullable(professorDisciplina);
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findAll() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd", ProfessorDisciplina.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public void delete(ProfessorDisciplinaId id) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            ProfessorDisciplina professorDisciplina = em.find(ProfessorDisciplina.class, id);
            if (professorDisciplina != null) {
                em.remove(professorDisciplina);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findByProfessorId(Long idProf) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd WHERE pd.professor.idProf = :idProf",
                    ProfessorDisciplina.class);
            query.setParameter("idProf", idProf);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findByDisciplinaId(Long idDisc) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd WHERE pd.disciplina.idDisc = :idDisc",
                    ProfessorDisciplina.class);
            query.setParameter("idDisc", idDisc);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findByDataAssociacao(LocalDate dtAssociacao) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd WHERE pd.dtAssociacao = :dtAssociacao",
                    ProfessorDisciplina.class);
            query.setParameter("dtAssociacao", dtAssociacao);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findByPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd WHERE pd.dtAssociacao BETWEEN :dataInicio AND :dataFim",
                    ProfessorDisciplina.class);
            query.setParameter("dataInicio", dataInicio);
            query.setParameter("dataFim", dataFim);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public boolean existsAssociacao(Long idProf, Long idDisc) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(pd) FROM ProfessorDisciplina pd " +
                            "WHERE pd.professor.idProf = :idProf AND pd.disciplina.idDisc = :idDisc",
                    Long.class);
            query.setParameter("idProf", idProf);
            query.setParameter("idDisc", idDisc);
            return query.getSingleResult() > 0;
        } finally {
            DB.closeConnection();
        }
    }


    public Long countDisciplinasByProfessor(Long idProf) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(pd) FROM ProfessorDisciplina pd WHERE pd.professor.idProf = :idProf",
                    Long.class);
            query.setParameter("idProf", idProf);
            return query.getSingleResult();
        } finally {
            DB.closeConnection();
        }
    }


    public Long countProfessoresByDisciplina(Long idDisc) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(pd) FROM ProfessorDisciplina pd WHERE pd.disciplina.idDisc = :idDisc",
                    Long.class);
            query.setParameter("idDisc", idDisc);
            return query.getSingleResult();
        } finally {
            DB.closeConnection();
        }
    }


    public boolean atualizarDataAssociacao(Long idProf, Long idDisc, LocalDate novaData) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();

            // Cria o ID composto
            ProfessorDisciplinaId id = new ProfessorDisciplinaId();
            id.setIdProf(idProf);
            id.setIdDisc(idDisc);

            // Busca a associação
            ProfessorDisciplina pd = em.find(ProfessorDisciplina.class, id);

            if (pd != null) {
                pd.setDtAssociacao(novaData);
                em.merge(pd);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findAllOrderByDataAssociacaoDesc() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd ORDER BY pd.dtAssociacao DESC",
                    ProfessorDisciplina.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public List<ProfessorDisciplina> findAllOrderByDataAssociacaoAsc() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<ProfessorDisciplina> query = em.createQuery(
                    "SELECT pd FROM ProfessorDisciplina pd ORDER BY pd.dtAssociacao ASC",
                    ProfessorDisciplina.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }


    public int deleteByProfessorId(Long idProf) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(
                    "DELETE FROM ProfessorDisciplina pd WHERE pd.professor.idProf = :idProf");
            query.setParameter("idProf", idProf);
            int result = query.executeUpdate();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }


    public int deleteByDisciplinaId(Long idDisc) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(
                    "DELETE FROM ProfessorDisciplina pd WHERE pd.disciplina.idDisc = :idDisc");
            query.setParameter("idDisc", idDisc);
            int result = query.executeUpdate();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }
}
