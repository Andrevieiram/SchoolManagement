package com.studentManagement.demo.controllers;

import com.studentManagement.demo.entities.Professor;
import com.studentManagement.demo.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> criar(@RequestBody Professor p) {
        try {
            service.salvar(p);
            return ResponseEntity.status(HttpStatus.CREATED).body("Professor cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable String id) {
        Professor prof = service.buscarPorId(id);
        if (prof != null) {
            return ResponseEntity.ok(prof);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Professor professor) {
        try {
            professor.setId(id);
            service.atualizar(id,professor);
            return ResponseEntity.ok("Dados do professor atualizados com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable String id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Professor deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro crítico ao deletar: " + e.getMessage());
        }
    }
}



