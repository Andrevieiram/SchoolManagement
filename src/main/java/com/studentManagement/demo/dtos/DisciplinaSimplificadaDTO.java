package com.studentManagement.demo.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DisciplinaSimplificadaDTO {
    private Integer idDisc;
    private String nome;
    private String area;
    private Integer cargaHr;
    private LocalDate dataAssociacao;

    public Integer getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(Integer idDisc) {
        this.idDisc = idDisc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCargaHr() {
        return cargaHr;
    }

    public void setCargaHr(Integer cargaHr) {
        this.cargaHr = cargaHr;
    }

    public LocalDate getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDate dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }
}