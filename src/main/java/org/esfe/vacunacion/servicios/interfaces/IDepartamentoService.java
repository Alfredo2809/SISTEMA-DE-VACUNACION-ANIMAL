package org.esfe.vacunacion.servicios.interfaces;
import org.esfe.vacunacion.modelos.Departamento;
import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {
    Departamento guardar(Departamento departamento);
    List<Departamento> obtenerTodos();
    Optional<Departamento> obtenerPorId(Long id);
    void eliminar(Long id);
}