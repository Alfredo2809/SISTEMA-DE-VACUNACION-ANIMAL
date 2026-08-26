package org.esfe.vacunacion.servicios.interfaces;
import org.esfe.vacunacion.modelos.Municipio;
import java.util.List;
import java.util.Optional;

public interface IMunicipioService {
    Municipio guardar(Municipio municipio);
    List<Municipio> obtenerTodos();
    Optional<Municipio> obtenerPorId(Long id);
    void eliminar(Long id);
}