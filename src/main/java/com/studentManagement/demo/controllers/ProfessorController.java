package com.studentManagement.demo.controllers;

import com.studentManagement.demo.dtos.DisciplinaSimplificadaDTO;
import com.studentManagement.demo.dtos.ProfessorDTO;
import com.studentManagement.demo.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController() {
        this.professorService = new ProfessorService();
    }


    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> buscarTodos() {
        List<ProfessorDTO> professores = professorService.buscarTodos();
        return ResponseEntity.ok(professores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable String id) {
        Optional<ProfessorDTO> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/area/{area}")
    public ResponseEntity<List<ProfessorDTO>> buscarPorArea(@PathVariable String area) {
        List<ProfessorDTO> professores = professorService.buscarPorArea(area);
        return ResponseEntity.ok(professores);
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProfessorDTO>> buscarPorNome(@PathVariable String nome) {
        List<ProfessorDTO> professores = professorService.buscarPorNome(nome);
        return ResponseEntity.ok(professores);
    }


    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ProfessorDTO> buscarPorCpf(@PathVariable String cpf) {
        Optional<ProfessorDTO> professor = professorService.buscarPorCpf(cpf);
        return professor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProfessorDTO> criar(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO novoProfessor = professorService.salvar(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizar(@PathVariable String id, @RequestBody ProfessorDTO professorDTO) {
        Optional<ProfessorDTO> professorAtualizado = professorService.atualizar(id, professorDTO);
        return professorAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        boolean excluido = professorService.excluir(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PostMapping("/{idProfessor}/disciplinas/{idDisciplina}")
    public ResponseEntity<Void> associarDisciplina(
            @PathVariable String idProfessor,
            @PathVariable Integer idDisciplina,
            @RequestParam(required = false) LocalDate dataAssociacao) {

        // Se data não for fornecida, usa a data atual
        LocalDate data = dataAssociacao != null ? dataAssociacao : LocalDate.now();

        boolean associado = professorService.associarDisciplina(idProfessor, idDisciplina, data);

        if (associado) {
            return ResponseEntity.ok().build();
        } else {
            // Verifica se o motivo da falha é porque já existe associação
            if (professorService.buscarPorId(idProfessor).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }


    @DeleteMapping("/{idProfessor}/disciplinas/{idDisciplina}")
    public ResponseEntity<Void> desassociarDisciplina(
            @PathVariable String idProfessor,
            @PathVariable Integer idDisciplina) {

        boolean desassociado = professorService.desassociarDisciplina(idProfessor, idDisciplina);
        return desassociado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/{idProfessor}/disciplinas")
    public ResponseEntity<List<DisciplinaSimplificadaDTO>> buscarDisciplinasDoProfessor(
            @PathVariable String idProfessor) {

        if (!professorService.buscarPorId(idProfessor).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<DisciplinaSimplificadaDTO> disciplinas = professorService.buscarDisciplinasDoProfessor(idProfessor);
        return ResponseEntity.ok(disciplinas);
    }
}


