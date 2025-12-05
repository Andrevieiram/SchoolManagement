package com.studentManagement.demo.controllers;

import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public List<Professor> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Professor criar(@RequestBody Professor p) {
        return service.salvar(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscar(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}



