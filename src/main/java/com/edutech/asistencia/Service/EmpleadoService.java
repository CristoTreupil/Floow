package com.edutech.asistencia.Service;

import com.edutech.asistencia.Model.Empleado;
import com.edutech.asistencia.Repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado crear(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> actualizar(Long id, Empleado empleadoActualizado) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleado.setNombre(empleadoActualizado.getNombre());
                    empleado.setApellido(empleadoActualizado.getApellido());
                    empleado.setDni(empleadoActualizado.getDni());
                    empleado.setPuesto(empleadoActualizado.getPuesto());
                    return empleadoRepository.save(empleado);
                });
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
