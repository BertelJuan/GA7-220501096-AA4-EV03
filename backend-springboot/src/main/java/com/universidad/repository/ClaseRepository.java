package com.universidad.repository;

import com.universidad.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    Optional<Clase> findByNombreAndDocenteId(String nombre, Long docenteId);
}
