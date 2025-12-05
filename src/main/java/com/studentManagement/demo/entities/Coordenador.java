package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "coordenadores")
public class Coordenador {

    @Id
    @Column(name = "idcoord")
    private String id;

    @Column(name = "curso_id", nullable = true)
    private Integer cursoId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "cpf")
    private Pessoa pessoa;

    public Coordenador(String id) {
        this.id = id;
    }

    public Coordenador() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
