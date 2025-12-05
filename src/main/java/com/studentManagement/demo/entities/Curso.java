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
    private Integer idCurso;

    @Column(name = "nomecurso")
    private String nomeCurso;

    // Relação com coordenador
    @OneToOne(mappedBy = "curso")
    private Coordenador coordenador;

    public Curso(Integer idCurso, String nomeCurso, Coordenador coordenador) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.coordenador = coordenador;
    }

    public Curso() {
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
}
