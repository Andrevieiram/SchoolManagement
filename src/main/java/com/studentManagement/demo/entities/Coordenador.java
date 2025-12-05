package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idpessoa")
@Table(name = "coordenadores")
public class Coordenador extends Pessoa {

    @Column(name = "idcoord")
    private String idCoord;

    // Relação com curso
    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Coordenador(String idCoord, Curso curso) {
        this.idCoord = idCoord;
        this.curso = curso;
    }

    public Coordenador() {
    }

    public String getIdCoord() {
        return idCoord;
    }

    public void setIdCoord(String idCoord) {
        this.idCoord = idCoord;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
