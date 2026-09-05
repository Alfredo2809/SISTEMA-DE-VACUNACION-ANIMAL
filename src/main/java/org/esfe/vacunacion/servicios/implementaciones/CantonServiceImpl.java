package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Canton;
import org.esfe.vacunacion.repositorios.CantonRepository;
import org.esfe.vacunacion.repositorios.MunicipioRepository;
import org.esfe.vacunacion.servicios.interfaces.ICantonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CantonServiceImpl implements ICantonService {

    @Autowired
    private CantonRepository cantonRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Canton guardar(Canton canton) {

        if (canton.getNombre() == null || canton.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cantón es obligatorio. Por favor complete la información.");
        }


        if (canton.getMunicipio() == null || canton.getMunicipio().getIdMunicipio() == null) {
            throw new IllegalArgumentException("Debe seleccionar un municipio obligatoriamente.");
        }

        Long idMunicipio = canton.getMunicipio().getIdMunicipio();


        if (!existeMunicipio(idMunicipio)) {
            throw new IllegalArgumentException("El municipio seleccionado no existe en el sistema.");
        }

        String nombreLimpio = canton.getNombre().trim();
        canton.setNombre(nombreLimpio);

        boolean existe;
        if (canton.getIdCanton() == null) {
            existe = cantonRepository.existsByNombreIgnoreCaseAndMunicipioIdMunicipio(nombreLimpio, idMunicipio);
        } else {
            existe = cantonRepository.existsByNombreIgnoreCaseAndMunicipioIdMunicipioAndIdCantonNot(nombreLimpio, idMunicipio, canton.getIdCanton());
        }

        if (existe) {
            throw new IllegalArgumentException("Ya existe un cantón con el nombre '" + nombreLimpio + "' en el municipio seleccionado.");
        }

        return cantonRepository.save(canton);
    }

    @Override
    public List<Canton> obtenerTodos() {
        return cantonRepository.findAll();
    }

    @Override
    public Page<Canton> obtenerPaginados(Pageable pageable) {
        return cantonRepository.findAll(pageable);
    }

    @Override
    public Optional<Canton> obtenerPorId(Long id) {
        return cantonRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        cantonRepository.deleteById(id);
    }

    @Override
    public boolean existeMunicipio(Long idMunicipio) {
        return idMunicipio != null && municipioRepository.existsById(idMunicipio);
    }
}