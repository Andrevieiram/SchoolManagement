package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddisc")
    private Integer idDisc;

    @Column(name = "nome")
    private String nome;

    @Column(name = "area")
    private String area;

    @Column(name = "cargahr")
    private Integer cargaHr;

    // Relação com ProfessorDisciplina
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfessorDisciplina> professorDisciplinas;

    public Disciplina(Integer idDisc, String nome, String area, Integer cargaHr, Set<ProfessorDisciplina> professorDisciplinas) {
        this.idDisc = idDisc;
        this.nome = nome;
        this.area = area;
        this.cargaHr = cargaHr;
        this.professorDisciplinas = professorDisciplinas;
    }

    public Disciplina() {
    }

    public Integer getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(Integer idDisc) {
        this.idDisc = idDisc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCargaHr() {
        return cargaHr;
    }

    public void setCargaHr(Integer cargaHr) {
        this.cargaHr = cargaHr;
    }

    public Set<ProfessorDisciplina> getProfessorDisciplinas() {
        return professorDisciplinas;
    }

    public void setProfessorDisciplinas(Set<ProfessorDisciplina> professorDisciplinas) {
        this.professorDisciplinas = professorDisciplinas;
    }
}
