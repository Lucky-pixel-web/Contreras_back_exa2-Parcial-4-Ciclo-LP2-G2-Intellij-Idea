package com.example.exa02.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TBL_CARRERAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carreras {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable=false, unique=true, length=100)
    private String nombre;
}