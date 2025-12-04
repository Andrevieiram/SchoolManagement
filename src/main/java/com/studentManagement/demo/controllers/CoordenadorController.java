package com.studentManagement.demo.controllers;

import com.studentManagement.demo.dtos.CoordenadorDTO;
import com.studentManagement.demo.services.CoordenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    public CoordenadorController() {
        this.coordenadorService = new CoordenadorService();
    }


    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> buscarTodos() {
        List<CoordenadorDTO> coordenadores = coordenadorService.buscarTodos();
        return ResponseEntity.ok(coordenadores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> buscarPorId(@PathVariable Long id) {
        Optional<CoordenadorDTO> coordenador = coordenadorService.buscarPorId(id);
        return coordenador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/coord/{idCoord}")
    public ResponseEntity<CoordenadorDTO> buscarPorIdCoord(@PathVariable Long idCoord) {
        Optional<CoordenadorDTO> coordenador = coordenadorService.buscarPorIdCoord(idCoord);
        return coordenador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<CoordenadorDTO> buscarPorCursoId(@PathVariable Long cursoId) {
        Optional<CoordenadorDTO> coordenador = coordenadorService.buscarPorCursoId(cursoId);
        return coordenador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CoordenadorDTO>> buscarPorNome(@PathVariable String nome) {
        List<CoordenadorDTO> coordenadores = coordenadorService.buscarPorNome(nome);
        return ResponseEntity.ok(coordenadores);
    }


    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CoordenadorDTO> buscarPorCpf(@PathVariable String cpf) {
        Optional<CoordenadorDTO> coordenador = coordenadorService.buscarPorCpf(cpf);
        return coordenador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<CoordenadorDTO> criar(@RequestBody CoordenadorDTO coordenadorDTO) {
        CoordenadorDTO novoCoordenador = coordenadorService.salvar(coordenadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCoordenador);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CoordenadorDTO> atualizar(@PathVariable Long id, @RequestBody CoordenadorDTO coordenadorDTO) {
        Optional<CoordenadorDTO> coordenadorAtualizado = coordenadorService.atualizar(id, coordenadorDTO);
        return coordenadorAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean excluido = coordenadorService.excluir(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PostMapping("/{idCoord}/cursos/{idCurso}")
    public ResponseEntity<Void> associarCurso(
            @PathVariable Long idCoord,
            @PathVariable Long idCurso) {

        boolean associado = coordenadorService.associarCurso(idCoord, idCurso);

        if (associado) {
            return ResponseEntity.ok().build();
        } else {
            // Verifica se o motivo da falha é porque o curso já tem coordenador
            if (coordenadorService.buscarPorCursoId(idCurso).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }


    @DeleteMapping("/{idCoord}/cursos")
    public ResponseEntity<Void> desassociarCurso(@PathVariable Long idCoord) {
        boolean desassociado = coordenadorService.desassociarCurso(idCoord);
        return desassociado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/{idCoord}/associado")
    public ResponseEntity<Boolean> verificarAssociacaoCurso(@PathVariable Long idCoord) {
        boolean associado = coordenadorService.isAssociadoACurso(idCoord);
        return ResponseEntity.ok(associado);
    }


}

