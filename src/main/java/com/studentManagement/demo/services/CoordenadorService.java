package com.studentManagement.demo.services;

import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.repo.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;

    public List<Coordenador> listarTodos() {
        return repository.findAll();
    }

    public Coordenador salvar(Coordenador c) {
        // Se não vier ID do front, geramos um UUID para garantir (String)
        if (c.getId() == null || c.getId().isEmpty()) {
            c.setId(UUID.randomUUID().toString());
        }
        // Garante que a Pessoa também tenha ID se for nova
        if (c.getPessoa() != null && (c.getPessoa().getId() == null || c.getPessoa().getId().isEmpty())) {
            c.getPessoa().setId(UUID.randomUUID().toString());
        }
        return repository.save(c);
    }

    public Optional<Coordenador> buscarPorId(String id) {
        return repository.findById(id);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
