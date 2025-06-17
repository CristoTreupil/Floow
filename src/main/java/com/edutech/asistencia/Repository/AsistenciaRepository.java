package com.edutech.asistencia.Repository;

import com.edutech.asistencia.Model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEmpleadoId(Long empleadoId);
    List<Asistencia> findByFechaHoraEntradaBetween(LocalDateTime inicio, LocalDateTime fin);
}