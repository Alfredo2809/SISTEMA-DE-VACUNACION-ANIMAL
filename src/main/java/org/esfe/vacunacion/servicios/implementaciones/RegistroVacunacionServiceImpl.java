package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.EstadoMascota;
import org.esfe.vacunacion.modelos.RegistroVacunacion;
import org.esfe.vacunacion.repositorios.RegistroVacunacionRepository;
import org.esfe.vacunacion.servicios.interfaces.IRegistroVacunacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroVacunacionServiceImpl implements IRegistroVacunacionService {

    @Autowired
    private RegistroVacunacionRepository registroVacunacionRepository;

    @Override
    public List<RegistroVacunacion> listar() {
        return registroVacunacionRepository.findAll();
    }

    @Override
    public Optional<RegistroVacunacion> buscarPorId(Long id) {
        return registroVacunacionRepository.findById(id);
    }

    @Override
    public RegistroVacunacion guardar(RegistroVacunacion registro) {
        validarRegistro(registro);
        return registroVacunacionRepository.save(registro);
    }

    @Override
    public void eliminar(Long id) {
        registroVacunacionRepository.deleteById(id);
    }

    private void validarRegistro(RegistroVacunacion registro) {
        // 1. Validar que la mascota exista y esté ACTIVA
        if (registro.getMascota() == null) {
            throw new IllegalArgumentException("Debe asociar una mascota al registro.");
        }
        if (registro.getMascota().getEstado() != EstadoMascota.ACTIVO) {
            throw new IllegalArgumentException("La mascota debe estar ACTIVA para registrar una vacunación.");
        }

        // 2. Validar que la campaña exista y esté en curso
        if (registro.getCampana() == null) {
            throw new IllegalArgumentException("Debe asociar una campaña de vacunación.");
        }

        LocalDate hoy = java.time.LocalDate.now();
        LocalDate inicio = registro.getCampana().getFechaInicio();
        LocalDate fin = registro.getCampana().getFechaFin();

        if (inicio != null && fin != null && (hoy.isBefore(inicio) || hoy.isAfter(fin))) {
            throw new IllegalArgumentException("La campaña de vacunación no se encuentra en curso.");
        }

        // 3. Validar usuario asignado
        if (registro.getUsuario() == null) {
            throw new IllegalArgumentException("El registro debe tener un usuario asignado.");
        }
    }
}
