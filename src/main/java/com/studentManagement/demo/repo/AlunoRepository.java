package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.Aluno;
import jakarta.transaction.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {

    @Modifying
    @Transactional
    @Query(value = "CALL InserirNovoAlunoEMatricula(" +
            ":idPessoa, :nome, :telefone, :email, " +
            ":idAluno, :dtNasc, " +
            ":idDisciplina, :idCurso, :status)", nativeQuery = true)
    void matricularAlunoCompleto(
            @Param("idPessoa") String idPessoa,
            @Param("nome") String nome,
            @Param("telefone") String telefone,
            @Param("email") String email,

            @Param("idAluno") String idAluno,
            @Param("dtNasc") Date dtNasc,

            @Param("idDisciplina") BigInteger idDisciplina,
            @Param("idCurso") BigInteger idCurso,
            @Param("status") String status
    );

    @Query(value = "SELECT verifica_choque(:idAluno, :idTurma)", nativeQuery = true)
    Boolean verificarChoqueHorario(@Param("idAluno") String idAluno, @Param("idTurma") BigDecimal idTurma);

    @Query(value = "SELECT calcula_cr(:idAluno)", nativeQuery = true)
    BigDecimal calcularCR(@Param("idAluno") String idAluno);
}