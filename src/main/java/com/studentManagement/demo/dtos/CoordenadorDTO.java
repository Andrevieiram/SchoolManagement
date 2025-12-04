package com.studentManagement.demo.dtos;

import lombok.Data;

@Data
public class CoordenadorDTO {
    private Long idCoord;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    // DTO simplificado para o curso gerenciado
    private CursoSimplificadoDTO curso;
}
