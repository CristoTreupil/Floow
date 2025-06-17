package com.edutech.asistencia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String puesto;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;
}
