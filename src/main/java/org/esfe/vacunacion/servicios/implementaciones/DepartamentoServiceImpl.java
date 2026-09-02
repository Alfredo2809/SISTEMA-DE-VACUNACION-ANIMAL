package org.esfe.vacunacion.servicios.implementaciones;
import org.esfe.vacunacion.modelos.Departamento;
import org.esfe.vacunacion.repositorios.DepartamentoRepository;
import org.esfe.vacunacion.servicios.interfaces.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public Departamento guardar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> obtenerPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        departamentoRepository.deleteById(id);
    }
}