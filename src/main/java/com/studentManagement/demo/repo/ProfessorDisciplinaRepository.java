package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.ProfessorDisciplina;
import com.studentManagement.demo.entities.utils.ProfessorDisciplinaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, ProfessorDisciplinaId> {

    // Buscar associações por professor
    List<ProfessorDisciplina> findByProfessorIdProf(Long idProf);

    // Buscar associações por disciplina
    List<ProfessorDisciplina> findByDisciplinaIdDisc(Long idDisc);

    // Buscar associações por data de associação
    List<ProfessorDisciplina> findByDtAssociacao(LocalDate dtAssociacao);

    // Buscar associações em um intervalo de datas
    @Query("SELECT pd FROM ProfessorDisciplina pd WHERE pd.dtAssociacao BETWEEN :dataInicio AND :dataFim")
    List<ProfessorDisciplina> findByPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);


}
