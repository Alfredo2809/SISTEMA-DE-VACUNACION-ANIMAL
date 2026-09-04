package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Departamento;
import org.esfe.vacunacion.repositorios.DepartamentoRepository;
import org.esfe.vacunacion.servicios.interfaces.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public Departamento guardar(Departamento departamento) {

        if (departamento.getNombre() == null || departamento.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento es obligatorio. Por favor complete la información.");
        }

        String nombreLimpio = departamento.getNombre().trim();
        departamento.setNombre(nombreLimpio);


        boolean existe;
        if (departamento.getIdDepartamento() == null) {
            existe = departamentoRepository.existsByNombreIgnoreCase(nombreLimpio);
        } else {
            existe = departamentoRepository.existsByNombreIgnoreCaseAndIdDepartamentoNot(nombreLimpio, departamento.getIdDepartamento());
        }

        if (existe) {
            throw new IllegalArgumentException("Ya existe un departamento registrado con el mismo nombre.");
        }


        return departamentoRepository.save(departamento);
    }

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Page<Departamento> obtenerPaginados(Pageable pageable) {
        return departamentoRepository.findAll(pageable);
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