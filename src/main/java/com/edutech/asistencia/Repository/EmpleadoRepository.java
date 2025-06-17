package com.edutech.asistencia.Repository;

import com.edutech.asistencia.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(String dni);
}