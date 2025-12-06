package com.studentManagement.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @Column(name = "idaluno", length = 50)
    private String id;

    @Column(name = "dtnasc")
    private Date dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf_aluno", referencedColumnName = "cpf")
    private Pessoa pessoa;

    @Column(name = "idhist")
    private Integer idHistorico;

    public Aluno() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

    public Pessoa getPessoa() { return pessoa; }
    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

    public Integer getIdHistorico() { return idHistorico; }
    public void setIdHistorico(Integer idHistorico) { this.idHistorico = idHistorico; }
}
