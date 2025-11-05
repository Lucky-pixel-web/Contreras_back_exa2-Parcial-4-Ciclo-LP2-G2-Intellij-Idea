package com.example.exa02.repository;

import com.example.exa02.entity.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudiantesRepository extends JpaRepository<Estudiantes, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}