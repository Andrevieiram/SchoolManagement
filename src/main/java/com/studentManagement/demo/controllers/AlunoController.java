package com.studentManagement.demo.controllers;

import com.studentManagement.demo.dto.DadosMatriculaDTO;
import com.studentManagement.demo.entities.Aluno;
import com.studentManagement.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<Aluno> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Aluno> aluno = service.buscarPorId(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Aluno não encontrado com o ID: " + id);
        }
    }

    // CRIAR (POST)
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Aluno aluno) {
        try {
            // Se o ID vier nulo, você pode gerar aqui ou no Service
            // if (aluno.getId() == null) aluno.setId(UUID.randomUUID().toString());

            Aluno novoAluno = service.salvar(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    // ATUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Aluno aluno) {
        try {
            // Chama o método atualizar do Service (que atualiza Pessoa e Aluno)
            Aluno atualizado = service.atualizar(id, aluno);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar: " + e.getMessage());
        }
    }

    // DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable String id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Aluno deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar aluno: " + e.getMessage());
        }
    }

    @PostMapping("/matricular")
    public ResponseEntity<?> realizarMatricula(@RequestBody DadosMatriculaDTO dados) {
        try {
            service.realizarMatriculaCompleta(dados);
            return ResponseEntity.ok("Aluno matriculado com sucesso via Procedure!");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Erro ao realizar matrícula: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/cr")
    public ResponseEntity<?> obterCR(@PathVariable String id) {
        try {
            BigDecimal cr = service.consultarCR(id);
            return ResponseEntity.ok().body("Coeficiente de Rendimento (CR): " + cr);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}