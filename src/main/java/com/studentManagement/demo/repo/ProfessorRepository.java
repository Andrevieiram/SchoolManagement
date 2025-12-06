package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.Professor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM professores WHERE cpf_prof = :cpf", nativeQuery = true)
    void deletarProfessorPorCpfDaPessoa(@Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM professores WHERE idprof = :id", nativeQuery = true)
    void deletarProfessorNaMarra(@Param("id") String id);
}