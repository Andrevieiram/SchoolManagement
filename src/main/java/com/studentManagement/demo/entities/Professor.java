package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "professores")
public class Professor  {

    @Id
    @Column(name = "idprof")
    private String id;

    private String area;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cpf_prof", referencedColumnName = "cpf")
    private Pessoa pessoa;

    public Professor(String id, String area, Pessoa pessoa) {
        this.id = id;
        this.area = area;
        this.pessoa = pessoa;
    }

    public Professor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
