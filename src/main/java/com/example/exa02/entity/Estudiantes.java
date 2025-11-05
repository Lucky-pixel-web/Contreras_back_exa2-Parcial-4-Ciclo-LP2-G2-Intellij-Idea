package com.example.exa02.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="TBL_ESTUDIANTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiantes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80)
    private String nombre;

    @Column(nullable=false, length=80)
    private String apellido;

    @Column(name="FECHA_NACIMIENTO", nullable=false)
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CARRERA_CODIGO", nullable=false)
    private Carreras carreras;
}