package com.studentManagement.demo.services;

import com.studentManagement.demo.dao.implementation.CoordenadorDAO;
import com.studentManagement.demo.dao.implementation.CursoDAO;
import com.studentManagement.demo.dtos.CoordenadorDTO;
import com.studentManagement.demo.dtos.CursoSimplificadoDTO;
import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.entities.Curso;
import com.studentManagement.demo.dtos.mapper.CoordenadorMapper;
import com.studentManagement.demo.repo.CoordenadorRepository;

import java.util.List;
import java.util.Optional;

public class CoordenadorService {

    private final CoordenadorDAO coordenadorDAO;
    private final CursoDAO cursoDAO;

    public CoordenadorService() {
        this.coordenadorDAO = new CoordenadorDAO();
        this.cursoDAO = new CursoDAO();
    }


    public List<CoordenadorDTO> buscarTodos() {
        List<Coordenador> coordenadores = coordenadorDAO.findAll();
        return CoordenadorMapper.toDTOList(coordenadores);
    }


    public Optional<CoordenadorDTO> buscarPorId(String id) {
        Optional<Coordenador> coordenador = coordenadorDAO.findById(id);
        return coordenador.map(CoordenadorMapper::toDTO);
    }


    public Optional<CoordenadorDTO> buscarPorIdCoord(String idCoord) {
        Optional<Coordenador> coordenador = coordenadorDAO.findByIdCoord(idCoord);
        return coordenador.map(CoordenadorMapper::toDTO);
    }


    public Optional<CoordenadorDTO> buscarPorCursoId(String cursoId) {
        Optional<Coordenador> coordenador = CoordenadorRepository.findByCursoIdCurso(cursoId);
        return coordenador.map(CoordenadorMapper::toDTO);
    }


    public List<CoordenadorDTO> buscarPorNome(String nome) {
        List<Coordenador> coordenadores = coordenadorDAO.findByNome(nome);
        return CoordenadorMapper.toDTOList(coordenadores);
    }


    public Optional<CoordenadorDTO> buscarPorCpf(String cpf) {
        Optional<Coordenador> coordenador = coordenadorDAO.findByCpf(cpf);
        return coordenador.map(CoordenadorMapper::toDTO);
    }


    public CoordenadorDTO salvar(CoordenadorDTO coordenadorDTO) {
        Coordenador coordenador = CoordenadorMapper.toEntity(coordenadorDTO);
        coordenador = coordenadorDAO.save(coordenador);
        return CoordenadorMapper.toDTO(coordenador);
    }


    public Optional<CoordenadorDTO> atualizar(String id, CoordenadorDTO coordenadorDTO) {
        if (!coordenadorDAO.findById(id).isPresent()) {
            return Optional.empty();
        }

        Coordenador coordenador = CoordenadorMapper.toEntity(coordenadorDTO);
        coordenador.setIdCoord(id);
        coordenador = coordenadorDAO.save(coordenador);
        return Optional.of(CoordenadorMapper.toDTO(coordenador));
    }


    public boolean excluir(String id) {
        Optional<Coordenador> coordenador = coordenadorDAO.findById(id);
        if (coordenador.isPresent()) {
            coordenadorDAO.delete(id);
            return true;
        }
        return false;
    }

    public boolean associarCurso(String idCoord, String idCurso) {
        Optional<Coordenador> coordenadorOpt = coordenadorDAO.findById(idCoord);
        Optional<Curso> cursoOpt = cursoDAO.findById(idCurso);

        if (coordenadorOpt.isPresent() && cursoOpt.isPresent()) {
            Coordenador coordenador = coordenadorOpt.get();
            Curso curso = cursoOpt.get();

            // Verificar se o curso já tem coordenador
            Optional<Coordenador> coordenadorExistente = coordenadorDAO.findByCursoId(idCurso);
            if (coordenadorExistente.isPresent() && !coordenadorExistente.get().getIdCoord().equals(idCoord)) {
                return false; // Curso já tem outro coordenador
            }

            try {
                coordenadorDAO.associarCurso(coordenador, curso);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }


    public boolean desassociarCurso(String idCoord) {
        Optional<Coordenador> coordenadorOpt = coordenadorDAO.findById(idCoord);

        if (coordenadorOpt.isPresent() && coordenadorOpt.get().getCurso() != null) {
            Coordenador coordenador = coordenadorOpt.get();
            Curso curso = coordenador.getCurso();

            coordenador.setCurso(null);
            curso.setCoordenador(null);

            try {
                coordenadorDAO.save(coordenador);
                cursoDAO.save(curso);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public boolean isAssociadoACurso(String idCoord) {
        return coordenadorDAO.isAssociadoACurso(idCoord);
    }
}
