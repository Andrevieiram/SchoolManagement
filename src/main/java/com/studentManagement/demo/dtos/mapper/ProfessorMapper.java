package com.studentManagement.demo.dtos.mapper;

import com.studentManagement.demo.dtos.DisciplinaSimplificadaDTO;
import com.studentManagement.demo.dtos.ProfessorDTO;
import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.entities.ProfessorDisciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorMapper {

    public static ProfessorDTO toDTO(Professor professor) {
        if (professor == null) {
            return null;
        }

        ProfessorDTO dto = new ProfessorDTO();
        dto.setIdProf(professor.getIdProf());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        dto.setCpf(professor.getCpf());
        dto.setTelefone(professor.getTelefone());
        dto.setArea(professor.getArea());

        // Converter disciplinas associadas
        if (professor.getProfessorDisciplinas() != null) {
            List<DisciplinaSimplificadaDTO> disciplinasDTO = professor.getProfessorDisciplinas().stream()
                    .map(pd -> {
                        DisciplinaSimplificadaDTO disciplinaDTO = new DisciplinaSimplificadaDTO();
                        disciplinaDTO.setIdDisc(pd.getDisciplina().getIdDisc());
                        disciplinaDTO.setNome(pd.getDisciplina().getNome());
                        disciplinaDTO.setArea(pd.getDisciplina().getArea());
                        disciplinaDTO.setCargaHr(pd.getDisciplina().getCargaHr());
                        disciplinaDTO.setDataAssociacao(pd.getDtAssociacao());
                        return disciplinaDTO;
                    })
                    .collect(Collectors.toList());

            dto.setDisciplinas(disciplinasDTO);
        } else {
            dto.setDisciplinas(new ArrayList<>());
        }

        return dto;
    }

    public static Professor toEntity(ProfessorDTO dto) {
        if (dto == null) {
            return null;
        }

        Professor professor = new Professor();
        professor.setIdProf(dto.getIdProf());
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setCpf(dto.getCpf());
        professor.setTelefone(dto.getTelefone());
        professor.setArea(dto.getArea());

        // Nota: A conversão de disciplinas para entidade geralmente é feita separadamente
        // para manter o controle sobre as associações

        return professor;
    }

    public static List<ProfessorDTO> toDTOList(List<Professor> professors) {
        if (professors == null) {
            return new ArrayList<>();
        }

        return professors.stream()
                .map(ProfessorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
