package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "professor")
public class Professor extends Pessoa {

    @Column(name = "idprof")
    private String idProf;

    @Column(name = "area")
    private String area;

    // Relação com ProfessorDisciplina
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfessorDisciplina> professorDisciplinas;

    public Professor(String idProf, String area, Set<ProfessorDisciplina> professorDisciplinas) {
        this.idProf = idProf;
        this.area = area;
        this.professorDisciplinas = professorDisciplinas;
    }

    public Professor() {
    }

    public String getIdProf() {
        return idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<ProfessorDisciplina> getProfessorDisciplinas() {
        return professorDisciplinas;
    }

    public void setProfessorDisciplinas(Set<ProfessorDisciplina> professorDisciplinas) {
        this.professorDisciplinas = professorDisciplinas;
    }
}
