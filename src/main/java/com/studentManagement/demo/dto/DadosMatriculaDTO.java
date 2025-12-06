package com.studentManagement.demo.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

public class DadosMatriculaDTO {

    // Dados da Pessoa (CPF é String/Varchar)
    private String idPessoa;
    private String nome;
    private String telefone;
    private String email;

    // Dados do Aluno (ID é String/Varchar)
    private String idAluno;
    private Date dtNasc;

    // Dados Acadêmicos (IDs são Long/int8)
    private BigInteger idDisciplina;
    private BigDecimal idTurma;
    private BigInteger idCurso;

    private String status; // "ATIVO"

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public BigInteger getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(BigInteger idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public BigDecimal getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(BigDecimal idTurma) {
        this.idTurma = idTurma;
    }

    public BigInteger getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(BigInteger idCurso) {
        this.idCurso = idCurso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
