package com.studentManagement.demo.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DisciplinaSimplificadaDTO {
    private Long idDisc;
    private String nome;
    private String area;
    private Integer cargaHr;
    private LocalDate dataAssociacao;  // Data da associação com o professor
}