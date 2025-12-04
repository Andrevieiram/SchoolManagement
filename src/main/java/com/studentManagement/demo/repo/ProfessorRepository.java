package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // Buscar por ID
    Optional<Professor> findByIdProf(Long idProf);

    // Buscar por área
    List<Professor> findByArea(String area);

    // Buscar por CPF
    @Query("SELECT p FROM Professor p WHERE p.cpf = :cpf")
    Optional<Professor> findByCpf(@Param("cpf") String cpf);

    // Contar professores por área
    @Query("SELECT COUNT(p) FROM Professor p WHERE p.area = :area")
    Long countByArea(@Param("area") String area);

}