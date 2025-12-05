package com.studentManagement.demo.dtos;

import lombok.Data;
import java.util.List;

@Data
public class ProfessorDTO {
    private String idProf;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String area;

    // DTO simplificado para disciplinas associadas
    private List<DisciplinaSimplificadaDTO> disciplinas;

    public String getIdProf() {
        return idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DisciplinaSimplificadaDTO> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaSimplificadaDTO> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
