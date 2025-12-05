package com.studentManagement.demo.entities.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class ProfessorDisciplinaId implements Serializable {

    @Column(name = "idprof")
    private String idProf;

    @Column(name = "iddisc")
    private Integer idDisc;

    public ProfessorDisciplinaId(String idProf, Integer idDisc) {
        this.idProf = idProf;
        this.idDisc = idDisc;
    }

    public ProfessorDisciplinaId() {
    }

    public String getIdProf() {
        return idProf;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public Integer getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(Integer idDisc) {
        this.idDisc = idDisc;
    }
}