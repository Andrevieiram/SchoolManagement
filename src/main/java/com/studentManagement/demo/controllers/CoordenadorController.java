package com.studentManagement.demo.controllers;

import com.studentManagement.demo.entities.Coordenador;
import com.studentManagement.demo.services.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {

    @Autowired
    private CoordenadorService service;

    @GetMapping
    public List<Coordenador> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Coordenador criar(@RequestBody Coordenador c) {
        return service.salvar(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordenador> buscar(@PathVariable String id) {
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

