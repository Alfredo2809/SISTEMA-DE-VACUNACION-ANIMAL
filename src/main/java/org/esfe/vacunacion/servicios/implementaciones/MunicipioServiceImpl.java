package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.repositorios.DepartamentoRepository;
import org.esfe.vacunacion.repositorios.MunicipioRepository;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServiceImpl implements IMunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public Municipio guardar(Municipio municipio) {

        if (municipio.getNombre() == null || municipio.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del municipio es obligatorio. Por favor complete la información.");
        }

        if (municipio.getDepartamento() == null || municipio.getDepartamento().getIdDepartamento() == null) {
            throw new IllegalArgumentException("Debe seleccionar un departamento obligatoriamente.");
        }

        Long idDepartamento = municipio.getDepartamento().getIdDepartamento();


        if (!existeDepartamento(idDepartamento)) {
            throw new IllegalArgumentException("El departamento seleccionado no existe en el sistema.");
        }

        String nombreLimpio = municipio.getNombre().trim();
        municipio.setNombre(nombreLimpio);


        boolean existe;
        if (municipio.getIdMunicipio() == null) {
            existe = municipioRepository.existsByNombreIgnoreCaseAndDepartamentoIdDepartamento(nombreLimpio, idDepartamento);
        } else {
            existe = municipioRepository.existsByNombreIgnoreCaseAndDepartamentoIdDepartamentoAndIdMunicipioNot(nombreLimpio, idDepartamento, municipio.getIdMunicipio());
        }

        if (existe) {
            throw new IllegalArgumentException("Ya existe un municipio con el nombre '" + nombreLimpio + "' en el departamento seleccionado.");
        }

        return municipioRepository.save(municipio);
    }

    @Override
    public List<Municipio> obtenerTodos() {
        return municipioRepository.findAll();
    }

    @Override
    public Page<Municipio> obtenerPaginados(Pageable pageable) {
        return municipioRepository.findAll(pageable);
    }

    @Override
    public Optional<Municipio> obtenerPorId(Long id) {
        return municipioRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        municipioRepository.deleteById(id);
    }

    @Override
    public boolean existeDepartamento(Long idDepartamento) {
        return idDepartamento != null && departamentoRepository.existsById(idDepartamento);
    }
}