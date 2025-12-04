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
    private Long idDisc;

    @Column(name = "nome")
    private String nome;

    @Column(name = "area")
    private String area;

    @Column(name = "cargahr")
    private Integer cargaHr;

    // Relação com ProfessorDisciplina
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProfessorDisciplina> professorDisciplinas;

}
