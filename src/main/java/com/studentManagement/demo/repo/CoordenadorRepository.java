package com.studentManagement.demo.repo;

import com.studentManagement.demo.entities.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {
}