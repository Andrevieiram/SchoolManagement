package com.studentManagement.demo.services;

import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public Optional<Professor> buscarPorId(String id) {
        return repository.findById(id);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}