package com.edutech.asistencia.Service;

import com.edutech.asistencia.Model.Asistencia;
import com.edutech.asistencia.Model.Empleado;
import com.edutech.asistencia.Model.EstadoAsistencia;
import com.edutech.asistencia.Repository.AsistenciaRepository;
import com.edutech.asistencia.Repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final EmpleadoRepository empleadoRepository;

    public Asistencia registrarEntrada(Long empleadoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Asistencia asistencia = new Asistencia();
        asistencia.setFechaHoraEntrada(LocalDateTime.now());
        asistencia.setEmpleado(empleado);
        asistencia.setEstado(calcularEstado(asistencia.getFechaHoraEntrada()));

        return asistenciaRepository.save(asistencia);
    }

    public Asistencia registrarSalida(Long asistenciaId) {
        Asistencia asistencia = asistenciaRepository.findById(asistenciaId)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

        asistencia.setFechaHoraSalida(LocalDateTime.now());
        return asistenciaRepository.save(asistencia);
    }

    private EstadoAsistencia calcularEstado(LocalDateTime fechaHoraEntrada) {
        // Lógica para determinar si es puntual, tardanza, etc.
        LocalTime horaEntrada = fechaHoraEntrada.toLocalTime();
        LocalTime horaLimite = LocalTime.of(9, 0); // Ejemplo: hora límite a las 9 AM

        return horaEntrada.isAfter(horaLimite) ? EstadoAsistencia.TARDANZA : EstadoAsistencia.PUNTUAL;
    }

    public List<Asistencia> obtenerAsistenciasPorEmpleado(Long empleadoId) {
        return asistenciaRepository.findByEmpleadoId(empleadoId);
    }

    public List<Asistencia> obtenerAsistenciasPorRango(LocalDateTime inicio, LocalDateTime fin) {
        return asistenciaRepository.findByFechaHoraEntradaBetween(inicio, fin);
    }
}