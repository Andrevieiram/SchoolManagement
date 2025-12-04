package com.studentManagement.demo.dao.implementation;

import com.studentManagement.demo.database.DB;
import com.studentManagement.demo.entities.Disciplina;
import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.entities.ProfessorDisciplina;

import com.studentManagement.demo.entities.utils.ProfessorDisciplinaId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO {

    public Professor save(Professor professor) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            if (professor.getIdProf() == null) {
                em.persist(professor);
            } else {
                professor = em.merge(professor);
            }
            em.getTransaction().commit();
            return professor;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Professor> findById(Long id) {
        EntityManager em = DB.openConnection();
        try {
            Professor professor = em.find(Professor.class, id);
            return Optional.ofNullable(professor);
        } finally {
            DB.closeConnection();
        }
    }

    public List<Professor> findAll() {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Professor> query = em.createQuery("SELECT p FROM Professor p", Professor.class);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public void delete(Long id) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();
            Professor professor = em.find(Professor.class, id);
            if (professor != null) {
                em.remove(professor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }

    public List<Professor> findByArea(String area) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Professor> query = em.createQuery(
                    "SELECT p FROM Professor p WHERE p.area = :area", Professor.class);
            query.setParameter("area", area);
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public List<Professor> findByNome(String nome) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Professor> query = em.createQuery(
                    "SELECT p FROM Professor p WHERE p.nome LIKE :nome", Professor.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            DB.closeConnection();
        }
    }

    public Optional<Professor> findByCpf(String cpf) {
        EntityManager em = DB.openConnection();
        try {
            TypedQuery<Professor> query = em.createQuery(
                    "SELECT p FROM Professor p WHERE p.cpf = :cpf", Professor.class);
            query.setParameter("cpf", cpf);
            List<Professor> result = query.getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            DB.closeConnection();
        }
    }

    public ProfessorDisciplina associarDisciplina(Professor professor, Disciplina disciplina, LocalDate dataAssociacao) {
        EntityManager em = DB.openConnection();
        try {
            em.getTransaction().begin();

            ProfessorDisciplinaId id = new ProfessorDisciplinaId();
            id.setIdProf(professor.getIdProf());
            id.setIdDisc(disciplina.getIdDisc());

            ProfessorDisciplina pd = new ProfessorDisciplina();
            pd.setId(id);
            pd.setProfessor(professor);
            pd.setDisciplina(disciplina);
            pd.setDtAssociacao(dataAssociacao);

            em.persist(pd);
            em.getTransaction().commit();

            return pd;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            DB.closeConnection();
        }
    }
}
