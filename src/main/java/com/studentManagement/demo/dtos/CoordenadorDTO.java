package com.studentManagement.demo.dtos;

import lombok.Data;

@Data
public class CoordenadorDTO {
    private String idCoord;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    // DTO simplificado para o curso gerenciado
    private CursoSimplificadoDTO curso;

    public String getIdCoord() {
        return idCoord;
    }

    public void setIdCoord(String idCoord) {
        this.idCoord = idCoord;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CursoSimplificadoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoSimplificadoDTO curso) {
        this.curso = curso;
    }
}
