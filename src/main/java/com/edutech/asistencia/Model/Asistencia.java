package com.edutech.asistencia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "asistencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHoraEntrada;

    private LocalDateTime fechaHoraSalida;

    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;
}

