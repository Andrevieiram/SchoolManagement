package com.studentManagement.demo.services;

import com.studentManagement.demo.dao.implementation.ProfessorDAO;
import com.studentManagement.demo.dao.implementation.DisciplinaDAO;
import com.studentManagement.demo.dao.implementation.ProfessorDisciplinaDAO;
import com.studentManagement.demo.dtos.ProfessorDTO;
import com.studentManagement.demo.dtos.DisciplinaSimplificadaDTO;
import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.entities.Disciplina;
import com.studentManagement.demo.entities.ProfessorDisciplina;
import com.studentManagement.demo.dtos.mapper.ProfessorMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfessorService {

    private final ProfessorDAO professorDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final ProfessorDisciplinaDAO professorDisciplinaDAO;

    public ProfessorService() {
        this.professorDAO = new ProfessorDAO();
        this.disciplinaDAO = new DisciplinaDAO();
        this.professorDisciplinaDAO = new ProfessorDisciplinaDAO();
    }


    public List<ProfessorDTO> buscarTodos() {
        List<Professor> professores = professorDAO.findAll();
        return ProfessorMapper.toDTOList(professores);
    }


    public Optional<ProfessorDTO> buscarPorId(String id) {
        Optional<Professor> professor = professorDAO.findById(id);
        return professor.map(ProfessorMapper::toDTO);
    }


    public List<ProfessorDTO> buscarPorArea(String area) {
        List<Professor> professores = professorDAO.findByArea(area);
        return ProfessorMapper.toDTOList(professores);
    }


    public List<ProfessorDTO> buscarPorNome(String nome) {
        List<Professor> professores = professorDAO.findByNome(nome);
        return ProfessorMapper.toDTOList(professores);
    }


    public Optional<ProfessorDTO> buscarPorCpf(String cpf) {
        Optional<Professor> professor = professorDAO.findByCpf(cpf);
        return professor.map(ProfessorMapper::toDTO);
    }


    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        Professor professor = ProfessorMapper.toEntity(professorDTO);
        professor = professorDAO.save(professor);
        return ProfessorMapper.toDTO(professor);
    }


    public Optional<ProfessorDTO> atualizar(String id, ProfessorDTO professorDTO) {
        if (!professorDAO.findById(id).isPresent()) {
            return Optional.empty();
        }

        Professor professor = ProfessorMapper.toEntity(professorDTO);
        professor.setIdProf(id);
        professor = professorDAO.save(professor);
        return Optional.of(ProfessorMapper.toDTO(professor));
    }


    public boolean excluir(String id) {
        Optional<Professor> professor = professorDAO.findById(id);
        if (professor.isPresent()) {
            professorDAO.delete(id);
            return true;
        }
        return false;
    }

    public boolean associarDisciplina(String idProfessor, Integer idDisciplina, LocalDate dataAssociacao) {
        Optional<Professor> professorOpt = professorDAO.findById(idProfessor);
        Optional<Disciplina> disciplinaOpt = disciplinaDAO.findById(idDisciplina);

        if (professorOpt.isPresent() && disciplinaOpt.isPresent()) {
            Professor professor = professorOpt.get();
            Disciplina disciplina = disciplinaOpt.get();

            // Verificar se já existe associação
            if (professorDisciplinaDAO.existsAssociacao(idProfessor, idDisciplina)) {
                return false; // Já existe associação
            }

            ProfessorDisciplina pd = professorDAO.associarDisciplina(professor, disciplina, dataAssociacao);
            return pd != null;
        }

        return false;
    }


    public boolean desassociarDisciplina(String idProfessor, Integer idDisciplina) {
        try {
            // Buscar a associação
            List<ProfessorDisciplina> associacoes = professorDisciplinaDAO.findByProfessorId(idProfessor)
                    .stream()
                    .filter(pd -> pd.getDisciplina().getIdDisc().equals(idDisciplina))
                    .collect(Collectors.toList());

            if (associacoes.isEmpty()) {
                return false;
            }

            // Excluir a associação
            for (ProfessorDisciplina pd : associacoes) {
                professorDisciplinaDAO.delete(pd.getId());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<DisciplinaSimplificadaDTO> buscarDisciplinasDoProfessor(String idProfessor) {
        List<ProfessorDisciplina> associacoes = professorDisciplinaDAO.findByProfessorId(idProfessor);

        return associacoes.stream().map(pd -> {
            DisciplinaSimplificadaDTO dto = new DisciplinaSimplificadaDTO();
            dto.setIdDisc(pd.getDisciplina().getIdDisc());
            dto.setNome(pd.getDisciplina().getNome());
            dto.setArea(pd.getDisciplina().getArea());
            dto.setCargaHr(pd.getDisciplina().getCargaHr());
            dto.setDataAssociacao(pd.getDtAssociacao());
            return dto;
        }).collect(Collectors.toList());
    }
}