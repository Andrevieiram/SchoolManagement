package com.studentManagement.demo.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso")
    private Long idCurso;

    @Column(name = "nomecurso")
    private String nomeCurso;

    // Relação com coordenador
    @OneToOne(mappedBy = "curso")
    private Coordenador coordenador;

}
