package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Especie;
import org.esfe.vacunacion.modelos.EstadoMascota;
import org.esfe.vacunacion.modelos.Mascota;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {

    List<Mascota> obtenerTodas();

    Optional<Mascota> obtenerPorId(Long idMascota);

    Mascota guardar(Mascota mascota);

    void eliminarPorId(Long idMascota);

    // Métodos requeridos en la tarea usando los tipos de datos correctos del modelo
    Mascota actualizarInformacion(Long idMascota, String nombre, Especie especie, Integer edadAproximadaMeses);

    Mascota cambiarEstado(Long idMascota, EstadoMascota nuevoEstado);
}