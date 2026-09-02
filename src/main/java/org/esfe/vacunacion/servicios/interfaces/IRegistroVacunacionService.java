package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.RegistroVacunacion;
import java.util.List;
import java.util.Optional;

public interface IRegistroVacunacionService {
    List<RegistroVacunacion> listar();
    Optional<RegistroVacunacion> buscarPorId(Long id);
    RegistroVacunacion guardar(RegistroVacunacion registro);
    void eliminar(Long id);
}
