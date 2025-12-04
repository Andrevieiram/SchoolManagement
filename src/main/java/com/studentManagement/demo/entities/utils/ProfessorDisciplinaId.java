package com.studentManagement.demo.entities.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class ProfessorDisciplinaId implements Serializable {

    @Column(name = "idprof")
    private Long idProf;

    @Column(name = "iddisc")
    private Long idDisc;

}