package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "coordenador")
public class Coordenador extends Pessoa {

    @Column(name = "idcoord")
    private Long idCoord;

    // Relação com curso
    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
