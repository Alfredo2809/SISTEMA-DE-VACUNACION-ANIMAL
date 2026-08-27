package org.esfe.vacunacion.servicios.interfaces;
import org.esfe.vacunacion.modelos.Colonia;
import java.util.List;
import java.util.Optional;

public interface IColoniaService {
    Colonia guardar(Colonia colonia);
    List<Colonia> obtenerTodos();
    Optional<Colonia> obtenerPorId(Long id);
    void eliminar(Long id);
    boolean existeCanton(Long idCanton);
}
