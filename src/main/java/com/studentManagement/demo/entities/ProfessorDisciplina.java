package com.studentManagement.demo.entities;

import com.studentManagement.demo.entities.utils.ProfessorDisciplinaId;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "professor_disciplina")
public class ProfessorDisciplina {

    @EmbeddedId
    public ProfessorDisciplinaId id;

    @ManyToOne
    @MapsId("idProf")
    @JoinColumn(name = "idprof")
    private Professor professor;

    @ManyToOne
    @MapsId("idDisc")
    @JoinColumn(name = "iddisc")
    private Disciplina disciplina;

    @Column(name = "dt_associacao")
    private LocalDate dtAssociacao;

    public ProfessorDisciplina(ProfessorDisciplinaId id, Professor professor, Disciplina disciplina, LocalDate dtAssociacao) {
        this.id = id;
        this.professor = professor;
        this.disciplina = disciplina;
        this.dtAssociacao = dtAssociacao;
    }

    public ProfessorDisciplina() {
    }

    public ProfessorDisciplinaId getId() {
        return id;
    }

    public void setId(ProfessorDisciplinaId id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDate getDtAssociacao() {
        return dtAssociacao;
    }

    public void setDtAssociacao(LocalDate dtAssociacao) {
        this.dtAssociacao = dtAssociacao;
    }
}
