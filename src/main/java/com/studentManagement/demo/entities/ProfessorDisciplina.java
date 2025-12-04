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
}
