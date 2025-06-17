package com.edutech.asistencia.Controller;

import com.edutech.asistencia.Model.Empleado;
import com.edutech.asistencia.Service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos() {
        return ResponseEntity.ok(empleadoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.crear(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}