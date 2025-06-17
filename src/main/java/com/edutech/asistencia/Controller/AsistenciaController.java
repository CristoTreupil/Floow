package com.edutech.asistencia.Controller;

import com.edutech.asistencia.Model.Asistencia;
import com.edutech.asistencia.Service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {
    private final AsistenciaService asistenciaService;

    @PostMapping("/entrada/{empleadoId}")
    public ResponseEntity<Asistencia> registrarEntrada(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(asistenciaService.registrarEntrada(empleadoId));
    }

    @PutMapping("/salida/{asistenciaId}")
    public ResponseEntity<Asistencia> registrarSalida(@PathVariable Long asistenciaId) {
        return ResponseEntity.ok(asistenciaService.registrarSalida(asistenciaId));
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Asistencia>> obtenerPorEmpleado(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(asistenciaService.obtenerAsistenciasPorEmpleado(empleadoId));
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> obtenerPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(asistenciaService.obtenerAsistenciasPorRango(inicio, fin));
    }
}