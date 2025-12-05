package com.studentManagement.demo.dao.implementation;

import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.entities.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CoordenadorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Coordenador save(Coordenador coordenador) {
        if (coordenador.getIdCoord() == null) {
            entityManager.persist(coordenador);
            return coordenador;
        } else {
            return entityManager.merge(coordenador);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Coordenador> findByCpf(String cpf) {
        TypedQuery<Coordenador> query = entityManager.createQuery(
                "SELECT c FROM Coordenador c WHERE c.cpf = :cpf",
                Coordenador.class);
        query.setParameter("cpf", cpf);
        List<Coordenador> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional(readOnly = true)
    public List<Coordenador> findByNome(String nome) {
        TypedQuery<Coordenador> query = entityManager.createQuery(
                "SELECT c FROM Coordenador c WHERE LOWER(c.nome) LIKE LOWER(:nome)",
                Coordenador.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<Coordenador> findById(Integer id) { // Mudei para Integer (Serial)
        Coordenador coordenador = entityManager.find(Coordenador.class, id);
        return Optional.ofNullable(coordenador);
    }

    @Transactional(readOnly = true)
    public List<Coordenador> findAll() {
        return entityManager.createQuery("SELECT c FROM Coordenador c", Coordenador.class).getResultList();
    }

    @Transactional
    public void delete(Integer id) { // Mudei para Integer
        Coordenador coordenador = entityManager.find(Coordenador.class, id);
        if (coordenador != null) {
            entityManager.remove(coordenador);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Coordenador> findByIdCoord(Integer idCoord) { // Mudei para Integer
        TypedQuery<Coordenador> query = entityManager.createQuery(
                "SELECT c FROM Coordenador c WHERE c.idCoord = :idCoord", Coordenador.class);
        query.setParameter("idCoord", idCoord);
        List<Coordenador> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional(readOnly = true)
    public Optional<Coordenador> findByCursoId(Integer cursoId) { // Mudei para Integer
        TypedQuery<Coordenador> query = entityManager.createQuery(
                "SELECT c FROM Coordenador c WHERE c.curso.idCurso = :cursoId", Coordenador.class);
        query.setParameter("cursoId", cursoId);
        List<Coordenador> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional
    public void associarCurso(Coordenador coordenador, Curso curso) {
        coordenador.setCurso(curso);
        // Se o relacionamento for bidirecional, atualize o outro lado também:
        // curso.setCoordenador(coordenador);
        entityManager.merge(coordenador);
        entityManager.merge(curso);
    }

    @Transactional(readOnly = true)
    public Object[] obterInfoCursoCoordenador(Integer idCoord) { // Mudei para Integer
        try {
            Query query = entityManager.createNativeQuery(
                    "SELECT c.idcurso, c.nomecurso, " +
                            "(SELECT COUNT(*) FROM disciplina d WHERE d.curso_id = c.idcurso) AS total_disciplinas, " +
                            "(SELECT COUNT(*) FROM aluno a JOIN matricula m ON a.idaluno = m.idaluno WHERE m.idcurso = c.idcurso) AS total_alunos " +
                            "FROM curso c " +
                            "JOIN coordenador co ON c.idcurso = co.curso_id " +
                            "WHERE co.idcoord = :idCoord");
            query.setParameter("idCoord", idCoord);
            List<Object[]> result = query.getResultList();
            return result

