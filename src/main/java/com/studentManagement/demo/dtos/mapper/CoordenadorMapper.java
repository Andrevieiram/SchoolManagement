package com.studentManagement.demo.dtos.mapper;

import com.studentManagement.demo.dtos.CoordenadorDTO;
import com.studentManagement.demo.dtos.CursoSimplificadoDTO;
import com.studentManagement.demo.entities.Coordenador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoordenadorMapper {

    public static CoordenadorDTO toDTO(Coordenador coordenador) {
        if (coordenador == null) {
            return null;
        }

        CoordenadorDTO dto = new CoordenadorDTO();
        dto.setIdCoord(coordenador.getIdCoord());
        dto.setNome(coordenador.getNome());
        dto.setEmail(coordenador.getEmail());
        dto.setCpf(coordenador.getCpf());
        dto.setTelefone(coordenador.getTelefone());

        // Converter curso associado
        if (coordenador.getCurso() != null) {
            CursoSimplificadoDTO cursoDTO = new CursoSimplificadoDTO();
            cursoDTO.setIdCurso(coordenador.getCurso().getIdCurso());
            cursoDTO.setNomeCurso(coordenador.getCurso().getNomeCurso());
            dto.setCurso(cursoDTO);
        }

        return dto;
    }

    public static Coordenador toEntity(CoordenadorDTO dto) {
        if (dto == null) {
            return null;
        }

        Coordenador coordenador = new Coordenador();
        coordenador.setIdCoord(dto.getIdCoord());
        coordenador.setNome(dto.getNome());
        coordenador.setEmail(dto.getEmail());
        coordenador.setCpf(dto.getCpf());
        coordenador.setTelefone(dto.getTelefone());

        // Nota: A conversão do curso para entidade geralmente é feita separadamente
        // para manter o controle sobre as associações

        return coordenador;
    }

    public static List<CoordenadorDTO> toDTOList(List<Coordenador> coordenadores) {
        if (coordenadores == null) {
            return new ArrayList<>();
        }

        return coordenadores.stream()
                .map(CoordenadorMapper::toDTO)
                .collect(Collectors.toList());
    }
}

