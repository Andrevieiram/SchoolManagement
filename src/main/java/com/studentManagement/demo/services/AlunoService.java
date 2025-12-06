package com.studentManagement.demo.services;

import com.studentManagement.demo.entities.Aluno;
import com.studentManagement.demo.dto.DadosMatriculaDTO;
import com.studentManagement.demo.repo.AlunoRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Optional<Aluno> buscarPorId(String id) {
        return repository.findById(id);
    }


    public Aluno salvar(Aluno aluno) {

        if (aluno.getPessoa() != null) {
            if (aluno.getPessoa().getId() == null || aluno.getPessoa().getId().isEmpty()) {
                aluno.getPessoa().setId(UUID.randomUUID().toString());
            }
        }

        if (aluno.getIdHistorico() == null) {
            aluno.setIdHistorico(null);
        }

        return repository.save(aluno);
    }


    public Aluno atualizar(String id, Aluno alunoAtualizado) {
        Aluno alunoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (alunoAtualizado.getPessoa() != null) {
            alunoExistente.getPessoa().setNome(alunoAtualizado.getPessoa().getNome());
            alunoExistente.getPessoa().setEmail(alunoAtualizado.getPessoa().getEmail());
            alunoExistente.getPessoa().setTelefone(alunoAtualizado.getPessoa().getTelefone());
        }

        if (alunoAtualizado.getDataNascimento() != null) {
            alunoExistente.setDataNascimento(alunoAtualizado.getDataNascimento());
        }

        return repository.save(alunoExistente);
    }


    public void deletar(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Aluno não encontrado para deleção");
        }
    }

    public void realizarMatriculaCompleta(DadosMatriculaDTO dados) {
        Boolean temChoque = repository.verificarChoqueHorario(dados.getIdAluno(), dados.getIdTurma());

        if (Boolean.TRUE.equals(temChoque)) {
            throw new RuntimeException("ERRO: Choque de horário detectado! O aluno já possui aula neste dia e horário.");
        }

        repository.matricularAlunoCompleto(
                dados.getIdPessoa(),
                dados.getNome(),
                dados.getTelefone(),
                dados.getEmail(),
                dados.getIdAluno(),
                dados.getDtNasc(),
                dados.getIdDisciplina(),
                dados.getIdCurso(),
                "ATIVO"
        );
    }

    public BigDecimal consultarCR(String idAluno) {
        Optional<Aluno> alunoExiste = repository.findById(idAluno);

        if (alunoExiste == null) {
            throw new RuntimeException("Aluno não encontrado.");
        }
        return repository.calcularCR(idAluno);
    }
}
