package com.studentManagement.demo.services;

import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    public Professor salvar(Professor p) {
        if (p.getId() == null || p.getId().isEmpty()) p.setId(UUID.randomUUID().toString());
        if (p.getPessoa() != null && (p.getPessoa().getId() == null)) {
            p.getPessoa().setId(UUID.randomUUID().toString());
        }
        return repository.save(p);
    }

    public Professor buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(String id) {
        Professor prof = repository.findById(id).orElse(null);

        if (prof != null) {
            String idProfessor = prof.getId();

            repository.deletarProfessorNaMarra(idProfessor);


        }else{
            throw new RuntimeException("Professor não existe");
        }
    }

    public Professor atualizar(String id, Professor professorAtualizado) {
        Professor professorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (professorAtualizado.getPessoa() != null) {
            professorExistente.getPessoa().setNome(professorAtualizado.getPessoa().getNome());
            professorExistente.getPessoa().setEmail(professorAtualizado.getPessoa().getEmail());
            professorExistente.getPessoa().setTelefone(professorAtualizado.getPessoa().getTelefone());

        }
        return repository.save(professorExistente);
    }
}