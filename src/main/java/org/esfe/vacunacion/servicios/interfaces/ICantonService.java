package org.esfe.vacunacion.servicios.interfaces;
import org.esfe.vacunacion.modelos.Canton;
import java.util.List;
import java.util.Optional;

public interface ICantonService {
    Canton guardar(Canton canton);
    List<Canton> obtenerTodos();
    Optional<Canton> obtenerPorId(Long id);
    void eliminar(Long id);
    boolean existeMunicipio(Long idMunicipio);
}