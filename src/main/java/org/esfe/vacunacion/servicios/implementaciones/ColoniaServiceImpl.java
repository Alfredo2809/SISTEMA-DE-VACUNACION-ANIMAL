package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Colonia;
import org.esfe.vacunacion.repositorios.CantonRepository;
import org.esfe.vacunacion.repositorios.ColoniaRepository;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColoniaServiceImpl implements IColoniaService {

    @Autowired
    private ColoniaRepository coloniaRepository;

    @Autowired
    private CantonRepository cantonRepository;

    @Override
    public Colonia guardar(Colonia colonia) {

        if (colonia.getNombre() == null || colonia.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la colonia es obligatorio. Por favor complete la información.");
        }

        if (colonia.getCanton() == null || colonia.getCanton().getIdCanton() == null) {
            throw new IllegalArgumentException("Debe seleccionar un cantón obligatoriamente.");
        }

        Long idCanton = colonia.getCanton().getIdCanton();


        if (!existeCanton(idCanton)) {
            throw new IllegalArgumentException("El cantón seleccionado no existe en el sistema.");
        }

        String nombreLimpio = colonia.getNombre().trim();
        colonia.setNombre(nombreLimpio);


        boolean existe;
        if (colonia.getIdColonia() == null) {
            existe = coloniaRepository.existsByNombreIgnoreCaseAndCantonIdCanton(nombreLimpio, idCanton);
        } else {
            existe = coloniaRepository.existsByNombreIgnoreCaseAndCantonIdCantonAndIdColoniaNot(nombreLimpio, idCanton, colonia.getIdColonia());
        }

        if (existe) {
            throw new IllegalArgumentException("Ya existe una colonia con el nombre '" + nombreLimpio + "' en el cantón seleccionado.");
        }

        return coloniaRepository.save(colonia);
    }

    @Override
    public List<Colonia> obtenerTodos() {
        return coloniaRepository.findAll();
    }

    @Override
    public Page<Colonia> obtenerPaginados(Pageable pageable) {
        return coloniaRepository.findAll(pageable);
    }

    @Override
    public Optional<Colonia> obtenerPorId(Long id) {
        return coloniaRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        coloniaRepository.deleteById(id);
    }

    @Override
    public boolean existeCanton(Long idCanton) {
        return idCanton != null && cantonRepository.existsById(idCanton);
    }
}