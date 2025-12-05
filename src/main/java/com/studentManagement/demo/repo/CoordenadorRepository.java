package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {

    // Buscar por nome (usando o nome da pessoa)
    @Query("SELECT c FROM Coordenador c WHERE c.nome LIKE %:nome%")
    List<Coordenador> findByNomeContaining(@Param("nome") String nome);


    // Buscar por CPF
    @Query("SELECT c FROM Coordenador c WHERE c.cpf = :cpf")
    Optional<Coordenador> findByCpf(@Param("cpf") String cpf);

    // Verificar se existe coordenador para um curso
    static Optional<Coordenador> findByCursoIdCurso(String idCurso) {
        return null;
    }

}