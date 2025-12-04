package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "professor")
public class Professor extends Pessoa {

    @Column(name = "idprof")
    private Long idProf;

    @Column(name = "area")
    private String area;

    // Relação com ProfessorDisciplina
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfessorDisciplina> professorDisciplinas;

}
