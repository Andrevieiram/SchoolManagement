package com.studentManagement.demo.dtos;

import lombok.Data;
import java.util.List;

@Data
public class ProfessorDTO {
    private Long idProf;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String area;

    // DTO simplificado para disciplinas associadas
    private List<DisciplinaSimplificadaDTO> disciplinas;
}
