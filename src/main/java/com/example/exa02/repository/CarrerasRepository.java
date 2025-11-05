package com.example.exa02.repository;

import com.example.exa02.entity.Carreras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrerasRepository extends JpaRepository<Carreras, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}